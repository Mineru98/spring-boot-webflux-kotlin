package com.mineru.webflux.domain.User

import com.mineru.webflux.annotation.ColumnPosition
import lombok.*
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.Comment
import javax.persistence.*

@Builder
@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자를 만들어줍니다.
@NoArgsConstructor // 파라미터가 없는 기본 생성자를 생성해준다
//@RequiredArgsConstructor // 어노테이션은 final이나 @NonNull인 필드 값만 파라미터로 받는 생성자를 만들어줌
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "User")
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnPosition(1)
    var id: Long? = null

    @Comment("계정 아이디")
    @Column(unique=true)
    @ColumnPosition(2)
    var account: String? = null

    @Comment("비밀번호")
    @Column
    @ColumnPosition(3)
    var password: String? = null

    @Comment("사용자 이름")
    @Column
    @ColumnPosition(4)
    var username: String? = null

    @Comment("사용자 프로필 이미지 URL")
    @Column
    @ColumnPosition(5)
    var userImageUrl: String? = null

    @Comment("가입 인증 이메일")
    @Column
    @ColumnPosition(6)
    var email: String? = null

    @Comment("사용자 소개글")
    @Column
    @ColumnPosition(7)
    var introduceDescription: String? = null

    @Comment("로그인 및 회원가입 방법")
    @Column
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'로컬'")
    @ColumnPosition(8)
    var loginProvider: LoginProvider? = null

    @Comment("사용자 권한")
    @Column
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'일반사용자'")
    @ColumnPosition(9)
    var userRole: UserRole? = null

    @Comment("사용자 계정 상태")
    @Column
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'활성'")
    @ColumnPosition(10)
    var useStatus: UserStatus? = null
}