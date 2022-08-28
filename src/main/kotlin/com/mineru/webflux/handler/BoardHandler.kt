package com.mineru.webflux.handler

import com.mineru.webflux.domain.Board.Board
import com.mineru.webflux.domain.Board.BoardReactiveRepository
import org.springframework.context.annotation.Lazy
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.notFound
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*

@Component
class BoardHandler(@Lazy private val boardReactiveRepository: BoardReactiveRepository) {
    fun getAll(req: ServerRequest): Mono<ServerResponse> = ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body<List<Board>>(Flux.just(boardReactiveRepository.findAll()))
            .switchIfEmpty(notFound().build())

    fun getById(req: ServerRequest): Mono<ServerResponse> = ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body<Board>(Mono.just(boardReactiveRepository.findById(req.pathVariable("id").toLong())))
            .switchIfEmpty(notFound().build())

    fun save(req: ServerRequest): Mono<ServerResponse> = ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(req.bodyToMono(Board::class.java)
                    .switchIfEmpty(Mono.empty())
                    .filter(Objects::nonNull)
                    .flatMap { board ->
                        Mono.fromCallable {
                            boardReactiveRepository.save(board)
                        }.then(Mono.just(board))
                    }
            ).switchIfEmpty(notFound().build())

    fun modifyById(req: ServerRequest): Mono<ServerResponse> =
        boardReactiveRepository.findById(req.pathVariable("id").toLong())
            .filter(Objects::nonNull)
            .flatMap { board ->
                boardReactiveRepository.save(board)
            }
            .flatMap {
                it?.let { ok().build() }
            }
            .switchIfEmpty(notFound().build())

    fun deleteById(req: ServerRequest): Mono<ServerResponse> =
        boardReactiveRepository.findById(req.pathVariable("id").toLong())
            .filter(Objects::nonNull)
            .flatMap { board -> ok().build(boardReactiveRepository.deleteById(board.id!!)) }
            .switchIfEmpty(notFound().build())
}