package com.mineru.webflux.handler

import com.mineru.webflux.domain.HashTag.HashTag
import com.mineru.webflux.domain.HashTag.HashTagRepository
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
class HashTagHandler(private val hashTagRepository: HashTagRepository) {
    fun getAll(req: ServerRequest): Mono<ServerResponse> = ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body<List<HashTag>>(Mono.just(hashTagRepository.findAll()))
            .switchIfEmpty(notFound().build())

    fun getById(req: ServerRequest): Mono<ServerResponse> = ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body<HashTag>(Mono.just(hashTagRepository.findById(req.pathVariable("id").toLong())))
            .switchIfEmpty(notFound().build())

    fun save(req: ServerRequest): Mono<ServerResponse> = ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(req.bodyToMono(HashTag::class.java)
                    .switchIfEmpty(Mono.empty())
                    .filter(Objects::nonNull)
                    .flatMap { board ->
                        Mono.fromCallable {
                            hashTagRepository.save(board)
                        }.then(Mono.just(board))
                    }
            ).switchIfEmpty(notFound().build())

    fun modifyById(req: ServerRequest): Mono<ServerResponse> = ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(Mono.justOrEmpty(hashTagRepository.findById(req.pathVariable("id").toLong()))
                    .switchIfEmpty(Mono.empty())
                    .filter(Objects::nonNull)
                    .flatMap { board ->
                        Mono.fromCallable {
                            hashTagRepository.save(board)
                        }.then(Mono.just(board))
                    }
            ).switchIfEmpty(notFound().build())

    fun deleteById(req: ServerRequest): Mono<ServerResponse> = ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(Mono.justOrEmpty(hashTagRepository.findById(req.pathVariable("id").toLong()))
                    .switchIfEmpty(Mono.empty())
                    .filter(Objects::nonNull)
                    .flatMap { board ->
                        Mono.fromCallable {
                            hashTagRepository.delete(board)
                        }.then(Mono.just(board))
                    }
            ).switchIfEmpty(notFound().build())
}