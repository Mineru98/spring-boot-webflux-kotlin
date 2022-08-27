package com.mineru.webflux.router

import com.mineru.webflux.handler.PostCategoryHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RequestPredicates.path
import org.springframework.web.reactive.function.server.RouterFunctions.nest
import org.springframework.web.reactive.function.server.router

@Configuration
class PostCategoryRouter (private val postCategoryHandler: PostCategoryHandler) {
    @Bean
    fun routerPostCategory() = nest(path("/post/categories"),
            router {
                listOf(
                        GET("", postCategoryHandler::getAll),
                        GET("/{id}", postCategoryHandler::getById),
                        POST("", postCategoryHandler::save),
                        PUT("/{id}", postCategoryHandler::modifyById),
                        DELETE("/{id}", postCategoryHandler::deleteById)
                )
            }
    )

}