package com.mineru.webflux.router

import com.mineru.webflux.domain.User.Dto.CreateUserDto
import com.mineru.webflux.domain.User.User
import com.mineru.webflux.handler.UserHandler
import lombok.RequiredArgsConstructor
import org.springframework.context.annotation.Bean
import org.springframework.web.bind.annotation.*
import org.springframework.web.reactive.function.server.RequestPredicates.path
import org.springframework.web.reactive.function.server.RouterFunctions.nest
import org.springframework.web.reactive.function.server.router
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import javax.validation.Valid

@RestController()
@RequestMapping(value = ["/users"])
@RequiredArgsConstructor
class UserRouter (private val userHandler: UserHandler) {
    @GetMapping("")
    fun all(): Flux<User> {
        return userHandler.getAll()
    }

    @PostMapping("")
    fun create(@RequestBody @Valid createUserDto: CreateUserDto): Mono<User> {
        return userHandler.save(createUserDto)
    }

    @Bean
    fun routerUser() = nest(path("/users"),
            router {
                listOf(
                        GET("/{id}", userHandler::getById),
                        PUT("/{id}", userHandler::modifyById),
                        DELETE("/{id}", userHandler::deleteById)
                )
            }
    )
}