package com.mineru.webflux.handler

import com.mineru.webflux.domain.Post.Post
import com.mineru.webflux.domain.Post.PostRepository
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
class PostHandler(private val postRepository: PostRepository) {

    fun getAll(req: ServerRequest): Mono<ServerResponse> = ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body<List<Post>>(Mono.just(postRepository.findAll()))
            .switchIfEmpty(notFound().build())

    fun getById(req: ServerRequest): Mono<ServerResponse> = ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body<Post>(Mono.just(postRepository.findById(req.pathVariable("id").toLong())))
            .switchIfEmpty(notFound().build())

    fun save(req: ServerRequest): Mono<ServerResponse> = ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(req.bodyToMono(Post::class.java)
                    .switchIfEmpty(Mono.empty())
                    .filter(Objects::nonNull)
                    .flatMap { post ->
                        Mono.fromCallable {
                            postRepository.save(post)
                        }.then(Mono.just(post))
                    }
            ).switchIfEmpty(notFound().build())

    fun modifyById(req: ServerRequest): Mono<ServerResponse> = ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(Mono.justOrEmpty(postRepository.findById(req.pathVariable("id").toLong()))
                    .switchIfEmpty(Mono.empty())
                    .filter(Objects::nonNull)
                    .flatMap { post ->
                        Mono.fromCallable {
                            postRepository.save(post)
                        }.then(Mono.just(post))
                    }
            ).switchIfEmpty(notFound().build())

    fun deleteById(req: ServerRequest): Mono<ServerResponse> = ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(Mono.justOrEmpty(postRepository.findById(req.pathVariable("id").toLong()))
                    .switchIfEmpty(Mono.empty())
                    .filter(Objects::nonNull)
                    .flatMap { post ->
                        Mono.fromCallable {
                            postRepository.delete(post)
                        }.then(Mono.just(post))
                    }
            ).switchIfEmpty(notFound().build())
}