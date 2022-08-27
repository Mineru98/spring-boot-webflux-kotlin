package com.mineru.webflux.domain.User

enum class UserRole(
        val value : String
) {
    USER("일반사용자"),
    ADMIN("관리자")
}