package com.mineru.webflux.domain.User.Dto

import com.mineru.webflux.domain.User.LoginProvider
import com.mineru.webflux.domain.User.User
import com.mineru.webflux.domain.User.UserRole
import com.mineru.webflux.domain.User.UserStatus
import lombok.Data
import reactor.core.publisher.Mono

@Data
class CreateUserDto {
    val account: String? = null
    val password: String? = null
    val username: String? = null
    val userImageUrl: String? = null
    val email: String? = null
    val introduceDescription: String? = null
    val loginProvider: LoginProvider? = null
    val userRole: UserRole? = null
    val userStatus: UserStatus? = null

    fun toEntity(): User {
        return User(
            id = null,
            account,
            password,
            username,
            userImageUrl,
            email,
            introduceDescription,
            loginProvider,
            userRole,
            userStatus
        )
    }

//    fun toEntity(): Mono<User> {
//        return Mono.just(
//            User(
//                id = null,
//                account,
//                password,
//                username,
//                userImageUrl,
//                email,
//                introduceDescription,
//                loginProvider,
//                userRole,
//                userStatus
//            )
//        )
//    }
}