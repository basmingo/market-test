package ru.neoflex.warehouse.repository

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service
import ru.neoflex.warehouse.service.dto.ProductDto
import java.time.LocalDateTime
import java.util.*

@Service
class ProductDao(private val jdbcTemplate: JdbcTemplate) {

    fun create(productDto: ProductDto) {
        jdbcTemplate
            .update(
                "INSERT INTO PUBLIC.PRODUCT VALUES (?, ?, ?, ?, ?)",
                productDto.productId,
                productDto.displayName,
                productDto.created,
                productDto.updated,
                productDto.status
            )
    }

    fun update(productId: UUID, status: String) {
        jdbcTemplate
            .update(
                "UPDATE PUBLIC.PRODUCT SET STATUS = ?, UPDATED = ? WHERE P_ID = ?",
                status,
                LocalDateTime.now(),
                productId
            )
    }

    fun delete(productId: UUID) {
        jdbcTemplate
            .update(
                "DELETE FROM PUBLIC.PRODUCT WHERE P_ID = ?",
                productId
            )
    }
}