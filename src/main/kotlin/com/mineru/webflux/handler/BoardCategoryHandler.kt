package com.mineru.webflux.handler

import com.mineru.webflux.domain.BoardCategory.BoardCategory
import com.mineru.webflux.domain.BoardCategory.BoardCategoryReactiveRepository
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
class BoardCategoryHandler(@Lazy private val boardCategoryReactiveRepository: BoardCategoryReactiveRepository) {
    fun getAll(req: ServerRequest): Mono<ServerResponse> = ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body<List<BoardCategory>>(Flux.just(boardCategoryReactiveRepository.findAll()))
            .switchIfEmpty(notFound().build())

    fun getById(req: ServerRequest): Mono<ServerResponse> = ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body<BoardCategory>(Mono.just(boardCategoryReactiveRepository.findById(req.pathVariable("id").toLong())))
            .switchIfEmpty(notFound().build())

    fun save(req: ServerRequest): Mono<ServerResponse> = ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(req.bodyToMono(BoardCategory::class.java)
                    .switchIfEmpty(Mono.empty())
                    .filter(Objects::nonNull)
                    .flatMap { boardCategory ->
                        Mono.fromCallable {
                            boardCategoryReactiveRepository.save(boardCategory)
                        }.then(Mono.just(boardCategory))
                    }
            ).switchIfEmpty(notFound().build())

    fun modifyById(req: ServerRequest): Mono<ServerResponse> =
        boardCategoryReactiveRepository.findById(req.pathVariable("id").toLong())
            .filter(Objects::nonNull)
            .flatMap { boardCategory ->
                boardCategoryReactiveRepository.save(boardCategory)
            }
            .flatMap {
                it?.let { ok().build() }
            }
            .switchIfEmpty(notFound().build())

    fun deleteById(req: ServerRequest): Mono<ServerResponse> =
        boardCategoryReactiveRepository.findById(req.pathVariable("id").toLong())
            .filter(Objects::nonNull)
            .flatMap { boardCategory -> ok().build(boardCategoryReactiveRepository.deleteById(boardCategory.id!!)) }
            .switchIfEmpty(notFound().build())
}