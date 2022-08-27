package com.mineru.webflux.handler

import com.mineru.webflux.domain.Board.Board
import com.mineru.webflux.domain.Board.BoardRepository
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.notFound
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Mono
import java.util.*

@Component
class BoardHandler(private val boardRepository: BoardRepository) {
    fun getAll(req: ServerRequest): Mono<ServerResponse> = ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body<List<Board>>(Mono.just(boardRepository.findAll()))
            .switchIfEmpty(notFound().build())

    fun getById(req: ServerRequest): Mono<ServerResponse> = ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body<Board>(Mono.just(boardRepository.findById(req.pathVariable("id").toLong())))
            .switchIfEmpty(notFound().build())

    fun save(req: ServerRequest): Mono<ServerResponse> = ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(req.bodyToMono(Board::class.java)
                    .switchIfEmpty(Mono.empty())
                    .filter(Objects::nonNull)
                    .flatMap { board ->
                        Mono.fromCallable {
                            boardRepository.save(board)
                        }.then(Mono.just(board))
                    }
            ).switchIfEmpty(notFound().build())

    fun modifyById(req: ServerRequest): Mono<ServerResponse> = ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(Mono.justOrEmpty(boardRepository.findById(req.pathVariable("id").toLong()))
                    .switchIfEmpty(Mono.empty())
                    .filter(Objects::nonNull)
                    .flatMap { board ->
                        Mono.fromCallable {
                            boardRepository.save(board)
                        }.then(Mono.just(board))
                    }
            ).switchIfEmpty(notFound().build())

    fun deleteById(req: ServerRequest): Mono<ServerResponse> = ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(Mono.justOrEmpty(boardRepository.findById(req.pathVariable("id").toLong()))
                    .switchIfEmpty(Mono.empty())
                    .filter(Objects::nonNull)
                    .flatMap { board ->
                        Mono.fromCallable {
                            boardRepository.delete(board)
                        }.then(Mono.just(board))
                    }
            ).switchIfEmpty(notFound().build())
}