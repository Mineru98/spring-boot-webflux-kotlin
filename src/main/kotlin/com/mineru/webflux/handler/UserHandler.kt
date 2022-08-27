package com.mineru.webflux.handler

import com.mineru.webflux.domain.User.User
import com.mineru.webflux.domain.User.UserRepository
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Mono
import java.util.*

@Component
class UserHandler(private val userRepository: UserRepository) {

    fun getAll(req: ServerRequest): Mono<ServerResponse> = ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body<List<User>>(Mono.just(userRepository.findAll()))
            .switchIfEmpty(ServerResponse.notFound().build())

    fun getById(req: ServerRequest): Mono<ServerResponse> = ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body<User>(Mono.just(userRepository.findById(req.pathVariable("id").toLong())))
            .switchIfEmpty(ServerResponse.notFound().build())

    fun save(req: ServerRequest): Mono<ServerResponse> = ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(req.bodyToMono(User::class.java)
                    .switchIfEmpty(Mono.empty())
                    .filter(Objects::nonNull)
                    .flatMap { user ->
                        Mono.fromCallable {
                            userRepository.save(user)
                        }.then(Mono.just(user))
                    }
            ).switchIfEmpty(ServerResponse.notFound().build())

    fun modifyById(req: ServerRequest): Mono<ServerResponse> = ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(Mono.justOrEmpty(userRepository.findById(req.pathVariable("id").toLong()))
                    .switchIfEmpty(Mono.empty())
                    .filter(Objects::nonNull)
                    .flatMap { user ->
                        Mono.fromCallable {
                            userRepository.save(user)
                        }.then(Mono.just(user))
                    }
            ).switchIfEmpty(ServerResponse.notFound().build())

    fun deleteById(req: ServerRequest): Mono<ServerResponse> = ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(Mono.justOrEmpty(userRepository.findById(req.pathVariable("id").toLong()))
                    .switchIfEmpty(Mono.empty())
                    .filter(Objects::nonNull)
                    .flatMap { user ->
                        Mono.fromCallable {
                            userRepository.delete(user)
                        }.then(Mono.just(user))
                    }
            ).switchIfEmpty(ServerResponse.notFound().build())
}