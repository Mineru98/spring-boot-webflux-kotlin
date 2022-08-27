package com.mineru.webflux.domain.BoardCategory

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BoardCategoryRepository: JpaRepository<BoardCategory, Long?> {
    
}