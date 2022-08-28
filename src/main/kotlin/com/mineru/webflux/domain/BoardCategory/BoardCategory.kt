package com.mineru.webflux.domain.BoardCategory

import com.mineru.webflux.annotation.ColumnPosition
import com.mineru.webflux.domain.Board.Board
import lombok.*
import org.hibernate.annotations.Comment
import org.springframework.data.jpa.repository.JpaRepository
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Column
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
@Table(name = "BoardCategory")
class BoardCategory {
    @Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnPosition(1)
    val id: Long? = null

    @Comment("카테고리명")
    @Column(unique = true)
    @ColumnPosition(2)
    val category: String? = null
}

@Repository
interface BoardCategoryRepository: JpaRepository<BoardCategory, Long?> {
}

interface BoardCategoryReactiveRepository: ReactiveCrudRepository<BoardCategory, Long?> {
}