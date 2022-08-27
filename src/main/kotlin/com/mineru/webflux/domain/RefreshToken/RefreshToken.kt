package com.mineru.webflux.domain.RefreshToken

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
@Table(name = "RefreshToken")
class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnPosition(1)
    val id: Long? = null

    @Comment("사용자 계정 아이디")
    @Column( unique = true)
    @ColumnPosition(2)
    val userAccount: String? = null

    @Comment("토큰 문자열")
    @Column
    @ColumnPosition(3)
    val token: String? = null
}