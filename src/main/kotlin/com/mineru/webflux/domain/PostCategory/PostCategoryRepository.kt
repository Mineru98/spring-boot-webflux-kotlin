package com.mineru.webflux.domain.PostCategory

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PostCategoryRepository: JpaRepository<PostCategory, Long?> {
    
}