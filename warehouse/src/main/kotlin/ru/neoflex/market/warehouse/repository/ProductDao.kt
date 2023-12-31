package ru.neoflex.market.warehouse.repository

import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service
import ru.neoflex.market.warehouse.service.dto.ProductDto
import ru.neoflex.market.warehouse.service.utils.TimeUtils
import java.time.LocalDateTime
import java.util.*

@Service
class ProductDao(private val jdbcTemplate: JdbcTemplate, private val timeUtils: TimeUtils) {

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

    fun getById(productId: UUID): ProductDto? = try {
        jdbcTemplate
            .queryForObject("SELECT P_ID, DISPLAY_NAME, CREATED, UPDATED, STATUS FROM PUBLIC.PRODUCT WHERE P_ID = '$productId'")
            { rs, _ ->
                val displayName = rs.getString("DISPLAY_NAME")
                val created = rs.getString("CREATED")
                val updated = rs.getString("UPDATED")
                val status = rs.getString("STATUS")
                ProductDto(
                    productId,
                    displayName,
                    timeUtils.parseH2Time(created),
                    timeUtils.parseH2Time(updated),
                    status
                )
            }
    } catch (e: EmptyResultDataAccessException) {
        null
    }

    fun update(productIds: List<UUID>, status: String) {
        jdbcTemplate
            .update(
                "UPDATE PUBLIC.PRODUCT SET STATUS = ?, UPDATED = ? WHERE P_ID IN ('${productIds.joinToString("', '")}')",
                status,
                LocalDateTime.now()
            )
    }

    fun delete(productId: UUID) {
        jdbcTemplate
            .update(
                "DELETE FROM PUBLIC.PRODUCT WHERE P_ID = ?",
                productId
            )
    }

    fun getAll(): List<ProductDto> =
        jdbcTemplate
            .query("SELECT P_ID, DISPLAY_NAME, CREATED, UPDATED, STATUS FROM PUBLIC.PRODUCT")
            { rs, _ ->
                val productId = rs.getString("P_ID")
                val displayName = rs.getString("DISPLAY_NAME")
                val created = rs.getString("CREATED")
                val updated = rs.getString("UPDATED")
                val status = rs.getString("STATUS")
                ProductDto(
                    UUID.fromString(productId),
                    displayName,
                    timeUtils.parseH2Time(created),
                    timeUtils.parseH2Time(updated),
                    status
                )
            }
}