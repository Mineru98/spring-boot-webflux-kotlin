package com.mineru.webflux.router

import com.mineru.webflux.handler.BoardCategoryHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RequestPredicates.path
import org.springframework.web.reactive.function.server.RouterFunctions.nest
import org.springframework.web.reactive.function.server.router

@Configuration
class BoardCategoryRouter (private val boardCategoryHandler: BoardCategoryHandler) {
    @Bean
    fun routerUser() = nest(path("/board/categories"),
            router {
                listOf(
                        GET("", boardCategoryHandler::getAll),
                        GET("/{id}", boardCategoryHandler::getById),
                        POST("", boardCategoryHandler::save),
                        PUT("/{id}", boardCategoryHandler::modifyById),
                        DELETE("/{id}", boardCategoryHandler::deleteById)
                )
            }
    )

}