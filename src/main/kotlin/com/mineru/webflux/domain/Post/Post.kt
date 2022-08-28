package com.mineru.webflux.domain.Post

import com.mineru.webflux.annotation.ColumnPosition
import com.mineru.webflux.domain.Board.Board
import com.mineru.webflux.domain.PostCategory.PostCategory
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
@Table(name = "HashTag")
class Post {
    @Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnPosition(1)
    val id: Long? = null

    @Comment("제목")
    @Column(length = 200)
    @ColumnPosition(2)
    val title: String? = null

    @Comment("게시글 내용")
    @Lob
    @Column
    @ColumnPosition(3)
    val content: String? = null

    @Comment("좋아요 개수")
    @Column
    @ColumnPosition(4)
    val likeCount: Long? = null

    @Comment("싫어요 개수")
    @Column
    @ColumnPosition(5)
    val notLikeCount: Long? = null

    @Comment("게시판 Id")
    @JoinColumn(name = "boardId")
    @ManyToOne
    @ColumnPosition(6)
    val board: Board? = null

    @Comment("게시글 카테고리 Id")
    @JoinColumn(name = "postCategoryId")
    @ManyToOne
    @ColumnPosition(7)
    val postCategory: PostCategory? = null

    @Comment("게시글 작성자 사용자 Id")
    @JoinColumn(name = "writerUserId")
    @ManyToOne
    @ColumnPosition(8)
    val writer: User? = null
}

@Repository
interface PostRepository: JpaRepository<Post, Long?> {
}

interface PostReactiveRepository: ReactiveCrudRepository<Post, Long?> {
}