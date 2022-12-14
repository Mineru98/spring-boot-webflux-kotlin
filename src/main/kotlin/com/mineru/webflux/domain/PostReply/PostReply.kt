package com.mineru.webflux.domain.PostReply

import com.mineru.webflux.annotation.ColumnPosition
import com.mineru.webflux.domain.Post.Post
import com.mineru.webflux.domain.User.User
import lombok.*
import org.hibernate.annotations.Comment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import javax.persistence.*

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "PostReply")
class PostReply {
    @Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnPosition(1)
    val id: Long? = null

    @Comment("댓글 내용")
    @Lob
    @Column(length = 100000)
    @ColumnPosition(2)
    val content: String? = null

    @Comment("게시글 Id")
    @JoinColumn(name = "postId")
    @ManyToOne
    @ColumnPosition(3)
    val post: Post? = null

    @Comment("댓글 작성자 사용자 Id")
    @JoinColumn(name = "writerUserId")
    @ManyToOne
    @ColumnPosition(4)
    val writer: User? = null
}

@Repository
interface PostReplyRepository: JpaRepository<PostReply, Long?> {
}

interface PostReplyReactiveRepository: ReactiveCrudRepository<PostReply, Long?> {
}