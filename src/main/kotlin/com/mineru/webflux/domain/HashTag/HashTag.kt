package com.mineru.webflux.domain.HashTag

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
@Table(name = "HashTag")
class HashTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnPosition(1)
    val id: Long? = null

    @Comment("해시태그명")
    @Column(unique = true)
    @ColumnPosition(2)
    val tagName: String? = null
}