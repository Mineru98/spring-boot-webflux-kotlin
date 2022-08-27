package com.mineru.webflux.domain.User

enum class UserStatus(
        val value : String
) {
    ACTIVE("활성"),
    DELETE("삭제"),
    BLOCKED("차단"),
    DORMANT("휴먼")
}