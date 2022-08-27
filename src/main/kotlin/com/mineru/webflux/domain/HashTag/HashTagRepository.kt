package com.mineru.webflux.domain.HashTag

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface HashTagRepository: JpaRepository<HashTag, Long?> {
    
}