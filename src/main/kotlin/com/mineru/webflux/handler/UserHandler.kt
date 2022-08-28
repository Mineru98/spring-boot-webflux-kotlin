package com.mineru.webflux.handler

import com.mineru.webflux.domain.User.Dto.CreateUserDto
import com.mineru.webflux.domain.User.User
import com.mineru.webflux.domain.User.UserReactiveRepository
import org.springframework.context.annotation.Lazy
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.notFound
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*

@Component
class UserHandler(@Lazy private val userReactiveRepository: UserReactiveRepository) {

    @Transactional(readOnly = true)
    fun getAll(): Flux<User> {
        return userReactiveRepository.findAll()
    }

    @Transactional(readOnly = true)
    fun getById(req: ServerRequest): Mono<ServerResponse> = ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body<User>(Mono.just(userReactiveRepository.findById(req.pathVariable("id").toLong())))
            .switchIfEmpty(notFound().build())

    @Transactional
    fun save(createUserDto: CreateUserDto): Mono<User> {
        val user = createUserDto.toEntity()
        return userReactiveRepository.save(user)
    }

    @Transactional
    fun modifyById(req: ServerRequest): Mono<ServerResponse> =
        userReactiveRepository.findById(req.pathVariable("id").toLong())
            .filter(Objects::nonNull)
            .flatMap { user ->
                userReactiveRepository.save(user)
            }
            .flatMap {
                it?.let { ok().build() }
            }
            .switchIfEmpty(notFound().build())

    @Transactional
    fun deleteById(req: ServerRequest): Mono<ServerResponse> =
        userReactiveRepository.findById(req.pathVariable("id").toLong())
            .filter(Objects::nonNull)
            .flatMap { user -> ok().build(userReactiveRepository.deleteById(user.id!!)) }
            .switchIfEmpty(notFound().build())
}