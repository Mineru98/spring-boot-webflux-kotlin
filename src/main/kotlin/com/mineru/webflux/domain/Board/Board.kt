package com.mineru.webflux.domain.Board

import com.mineru.webflux.annotation.ColumnPosition
import com.mineru.webflux.domain.BoardCategory.BoardCategory
import com.mineru.webflux.domain.User.User
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
@Table(name = "Board")
class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnPosition(1)
    val id: Long? = null

    @Comment("게시판 제목")
    @Column(length = 200)
    @ColumnPosition(2)
    val title: String? = null

    @Comment("게시판 설명")
    @Column(length = 300)
    @ColumnPosition(3)
    val description: String? = null

    @Comment("게시판 대표 이미지")
    @Column
    @ColumnPosition(4)
    val thumnailUrl: String? = null

    @Comment("게시판 관리자 사용자 Id")
    @JoinColumn(name = "boardCategoryId")
    @ManyToOne
    @ColumnPosition(5)
    val boardCategory: BoardCategory? = null

    @Comment("게시판 관리자 사용자 Id")
    @JoinColumn(name = "managerUserId")
    @ManyToOne
    @ColumnPosition(6)
    val manager: User? = null
}