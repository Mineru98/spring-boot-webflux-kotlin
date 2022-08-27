package com.mineru.webflux.domain.PostHashTag

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PostHashTagRepository: JpaRepository<PostHashTag, Long?> {
    
}