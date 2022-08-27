package com.mineru.webflux.handler

import com.mineru.webflux.domain.PostReply.PostReply
import com.mineru.webflux.domain.PostReply.PostReplyRepository
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
class PostReplyHandler(private val postReplyRepository: PostReplyRepository) {

    fun getAll(req: ServerRequest): Mono<ServerResponse> = ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body<List<PostReply>>(Mono.just(postReplyRepository.findAll()))
            .switchIfEmpty(notFound().build())

    fun getById(req: ServerRequest): Mono<ServerResponse> = ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body<PostReply>(Mono.just(postReplyRepository.findById(req.pathVariable("id").toLong())))
            .switchIfEmpty(notFound().build())

    fun save(req: ServerRequest): Mono<ServerResponse> = ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(req.bodyToMono(PostReply::class.java)
                    .switchIfEmpty(Mono.empty())
                    .filter(Objects::nonNull)
                    .flatMap { post ->
                        Mono.fromCallable {
                            postReplyRepository.save(post)
                        }.then(Mono.just(post))
                    }
            ).switchIfEmpty(notFound().build())

    fun modifyById(req: ServerRequest): Mono<ServerResponse> = ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(Mono.justOrEmpty(postReplyRepository.findById(req.pathVariable("id").toLong()))
                    .switchIfEmpty(Mono.empty())
                    .filter(Objects::nonNull)
                    .flatMap { post ->
                        Mono.fromCallable {
                            postReplyRepository.save(post)
                        }.then(Mono.just(post))
                    }
            ).switchIfEmpty(notFound().build())

    fun deleteById(req: ServerRequest): Mono<ServerResponse> = ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(Mono.justOrEmpty(postReplyRepository.findById(req.pathVariable("id").toLong()))
                    .switchIfEmpty(Mono.empty())
                    .filter(Objects::nonNull)
                    .flatMap { post ->
                        Mono.fromCallable {
                            postReplyRepository.delete(post)
                        }.then(Mono.just(post))
                    }
            ).switchIfEmpty(notFound().build())
}