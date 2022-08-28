package com.mineru.webflux.domain.RefreshToken

import com.mineru.webflux.annotation.ColumnPosition
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
@Table(name = "RefreshToken")
class RefreshToken {
    @Id
    @org.springframework.data.annotation.Id
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

@Repository
interface RefreshTokenRepository: JpaRepository<RefreshToken, Long?> {
}

interface RefreshTokenReactiveRepository: ReactiveCrudRepository<RefreshToken, Long?> {
}