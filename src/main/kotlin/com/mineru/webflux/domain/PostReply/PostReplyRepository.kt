package com.mineru.webflux.domain.PostReply

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PostReplyRepository: JpaRepository<PostReply, Long?> {
    
}