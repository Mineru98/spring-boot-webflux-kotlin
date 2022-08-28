package com.mineru.webflux.handler

import com.mineru.webflux.domain.PostReply.PostReply
import com.mineru.webflux.domain.PostReply.PostReplyReactiveRepository
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
class PostReplyHandler(@Lazy private val postReplyReactiveRepository: PostReplyReactiveRepository) {

    fun getAll(req: ServerRequest): Mono<ServerResponse> = ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body<List<PostReply>>(Flux.just(postReplyReactiveRepository.findAll()))
            .switchIfEmpty(notFound().build())

    fun getById(req: ServerRequest): Mono<ServerResponse> = ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body<PostReply>(Mono.just(postReplyReactiveRepository.findById(req.pathVariable("id").toLong())))
            .switchIfEmpty(notFound().build())

    fun save(req: ServerRequest): Mono<ServerResponse> = ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(req.bodyToMono(PostReply::class.java)
                    .switchIfEmpty(Mono.empty())
                    .filter(Objects::nonNull)
                    .flatMap { postReply ->
                        Mono.fromCallable {
                            postReplyReactiveRepository.save(postReply)
                        }.then(Mono.just(postReply))
                    }
            ).switchIfEmpty(notFound().build())


    fun modifyById(req: ServerRequest): Mono<ServerResponse> =
        postReplyReactiveRepository.findById(req.pathVariable("id").toLong())
            .filter(Objects::nonNull)
            .flatMap { postReply ->
                postReplyReactiveRepository.save(postReply)
            }
            .flatMap {
                it?.let { ok().build() }
            }
            .switchIfEmpty(notFound().build())

    fun deleteById(req: ServerRequest): Mono<ServerResponse> =
        postReplyReactiveRepository.findById(req.pathVariable("id").toLong())
            .filter(Objects::nonNull)
            .flatMap { postReply -> ok().build(postReplyReactiveRepository.deleteById(postReply.id!!)) }
            .switchIfEmpty(notFound().build())
}