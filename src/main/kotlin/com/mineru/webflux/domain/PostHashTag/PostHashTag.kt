package com.mineru.webflux.domain.PostHashTag

import com.mineru.webflux.annotation.ColumnPosition
import com.mineru.webflux.domain.HashTag.HashTag
import com.mineru.webflux.domain.Post.Post
import lombok.*
import org.hibernate.annotations.Comment
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