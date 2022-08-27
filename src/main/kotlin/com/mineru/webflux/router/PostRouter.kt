package com.mineru.webflux.router

import com.mineru.webflux.handler.PostHandler
import com.mineru.webflux.handler.PostReplyHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RequestPredicates.path
import org.springframework.web.reactive.function.server.RouterFunctions.nest
import org.springframework.web.reactive.function.server.router

@Configuration
class PostRouter(
        private val postHandler: PostHandler,
        private val postReplyHandler: PostReplyHandler
) {
    @Bean
    fun routerPost() = nest(path("/posts"),
            router {
                listOf(
                        GET("", postHandler::getAll),
                        GET("/{id}", postHandler::getById),
                        GET("/{postId}/reply", postReplyHandler::getAll),
                        POST("", postHandler::save),
                        PUT("/{id}", postHandler::modifyById),
                        PUT("/postId/reply/{id}", postReplyHandler::modifyById),
                        DELETE("/{id}", postHandler::deleteById),
                        DELETE("/postId/reply/{id}", postReplyHandler::deleteById),
                )
            }
    )
}