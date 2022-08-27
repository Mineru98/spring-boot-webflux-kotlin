package com.mineru.webflux.router

import com.mineru.webflux.handler.UserHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RequestPredicates.path
import org.springframework.web.reactive.function.server.RouterFunctions.nest
import org.springframework.web.reactive.function.server.router

@Configuration
class UserRouter (private val userHandler: UserHandler) {
    @Bean
    fun routerUser() = nest(path("/users"),
            router {
                listOf(
                        GET("", userHandler::getAll),
                        GET("/{id}", userHandler::getById),
                        POST("", userHandler::save),
                        PUT("/{id}", userHandler::modifyById),
                        DELETE("/{id}", userHandler::deleteById)
                )
            }
    )

}