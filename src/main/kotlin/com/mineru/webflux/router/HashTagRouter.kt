package com.mineru.webflux.router

import com.mineru.webflux.handler.HashTagHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RequestPredicates.path
import org.springframework.web.reactive.function.server.RouterFunctions.nest
import org.springframework.web.reactive.function.server.router

@Configuration
class HashTagRouter (private val hashTagHandler: HashTagHandler) {
    @Bean
    fun routerHashTag() = nest(path("/tags"),
            router {
                listOf(
                        GET("", hashTagHandler::getAll),
                        GET("/{id}", hashTagHandler::getById),
                        POST("", hashTagHandler::save),
                        PUT("/{id}", hashTagHandler::modifyById),
                        DELETE("/{id}", hashTagHandler::deleteById)
                )
            }
    )

}