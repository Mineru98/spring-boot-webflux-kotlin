package com.mineru.webflux.domain.PostCategory

import com.mineru.webflux.annotation.ColumnPosition
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
@Table(name = "PostCategory")
class PostCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnPosition(1)
    val id: Long? = null

    @Comment("카테고리명")
    @Column(unique = true)
    @ColumnPosition(2)
    val category: String? = null
}