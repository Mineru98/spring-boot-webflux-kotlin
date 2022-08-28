package com.mineru.webflux.domain.PostHashTag

import com.mineru.webflux.annotation.ColumnPosition
import com.mineru.webflux.domain.HashTag.HashTag
import com.mineru.webflux.domain.Post.Post
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
@Table(name = "PostHashTag")
class PostHashTag {
    @Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnPosition(1)
    val id: Long? = null

    @Comment("게시글 해시태그 Id")
    @JoinColumn(name = "hashTagId")
    @ManyToOne
    @ColumnPosition(2)
    val hashTag: HashTag? = null

    @Comment("게시글 Id")
    @JoinColumn(name = "postId")
    @ManyToOne
    @ColumnPosition(3)
    val post: Post? = null
}

@Repository
interface PostHashTagRepository: JpaRepository<PostHashTag, Long?> {
}

interface PostHashTagReactiveRepository: ReactiveCrudRepository<PostHashTag, Long?> {
}