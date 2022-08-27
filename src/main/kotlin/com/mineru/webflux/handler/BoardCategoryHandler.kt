package com.mineru.webflux.handler

import com.mineru.webflux.domain.BoardCategory.BoardCategory
import com.mineru.webflux.domain.BoardCategory.BoardCategoryRepository
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
class BoardCategoryHandler(private val boardCategoryRepository: BoardCategoryRepository) {
    fun getAll(req: ServerRequest): Mono<ServerResponse> = ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body<List<BoardCategory>>(Mono.just(boardCategoryRepository.findAll()))
            .switchIfEmpty(notFound().build())

    fun getById(req: ServerRequest): Mono<ServerResponse> = ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body<BoardCategory>(Mono.just(boardCategoryRepository.findById(req.pathVariable("id").toLong())))
            .switchIfEmpty(notFound().build())

    fun save(req: ServerRequest): Mono<ServerResponse> = ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(req.bodyToMono(BoardCategory::class.java)
                    .switchIfEmpty(Mono.empty())
                    .filter(Objects::nonNull)
                    .flatMap { board ->
                        Mono.fromCallable {
                            boardCategoryRepository.save(board)
                        }.then(Mono.just(board))
                    }
            ).switchIfEmpty(notFound().build())

    fun modifyById(req: ServerRequest): Mono<ServerResponse> = ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(Mono.justOrEmpty(boardCategoryRepository.findById(req.pathVariable("id").toLong()))
                    .switchIfEmpty(Mono.empty())
                    .filter(Objects::nonNull)
                    .flatMap { board ->
                        Mono.fromCallable {
                            boardCategoryRepository.save(board)
                        }.then(Mono.just(board))
                    }
            ).switchIfEmpty(notFound().build())

    fun deleteById(req: ServerRequest): Mono<ServerResponse> = ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(Mono.justOrEmpty(boardCategoryRepository.findById(req.pathVariable("id").toLong()))
                    .switchIfEmpty(Mono.empty())
                    .filter(Objects::nonNull)
                    .flatMap { board ->
                        Mono.fromCallable {
                            boardCategoryRepository.delete(board)
                        }.then(Mono.just(board))
                    }
            ).switchIfEmpty(notFound().build())
}