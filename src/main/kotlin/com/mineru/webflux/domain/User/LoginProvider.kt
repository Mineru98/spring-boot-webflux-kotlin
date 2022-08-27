package com.mineru.webflux.domain.User

enum class LoginProvider (val value : String) {
    LOCAL("로컬"),
    KAKAO("카카오"),
    GOOGLE("구글"),
    NAVER("네이버"),
    FB("페이스북")
}