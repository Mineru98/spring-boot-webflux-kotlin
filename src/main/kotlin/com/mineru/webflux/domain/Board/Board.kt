package com.mineru.webflux.domain.Board

import com.mineru.webflux.annotation.ColumnPosition
import com.mineru.webflux.domain.BoardCategory.BoardCategory
import com.mineru.webflux.domain.User.User
import lombok.*
import org.hibernate.annotations.Comment
import org.springframework.data.jpa.repository.JpaRepository
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Column
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import org.springframework.data.relational.core.mapping.*
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

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
    @org.springframework.data.annotation.Id
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

@Repository
interface BoardRepository: JpaRepository<Board, Long?> {
}

interface BoardReactiveRepository: ReactiveCrudRepository<Board, Long?> {
}