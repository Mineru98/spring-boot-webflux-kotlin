package com.mineru.webflux.router

import com.mineru.webflux.handler.BoardHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RequestPredicates.path
import org.springframework.web.reactive.function.server.RouterFunctions.nest
import org.springframework.web.reactive.function.server.router

@Configuration
class BoardRouter (private val boardHandler: BoardHandler) {
    @Bean
    fun routerUser() = nest(path("/boards"),
            router {
                listOf(
                        GET("", boardHandler::getAll),
                        GET("/{id}", boardHandler::getById),
                        POST("", boardHandler::save),
                        PUT("/{id}", boardHandler::modifyById),
                        DELETE("/{id}", boardHandler::deleteById)
                )
            }
    )

}