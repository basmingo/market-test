package ru.neoflex.market.order.repository

import io.camunda.common.auth.Product
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.queryForObject
import org.springframework.stereotype.Service
import ru.neoflex.market.order.service.dto.ProductDto
import java.time.LocalDateTime
import java.util.*

@Service
class ProductDao(val jdbcTemplate: JdbcTemplate) {

    fun getByOrderId(orderId: UUID): List<ProductDto> {
        return jdbcTemplate
            .query(
                "SELECT P_ID, CREATED, ORDER_ID FROM PUBLIC.PRODUCT WHERE ORDER_ID = '$orderId'"
            ) { rs, _ ->
                ProductDto(UUID.fromString(rs.getString("P_ID")), LocalDateTime.now(), orderId)
            }
    }

    fun insert(productDto: ProductDto) {
        jdbcTemplate
            .update(
                "INSERT INTO PUBLIC.PRODUCT (P_ID, CREATED, ORDER_ID) VALUES (?, ?, ?)",
                productDto.productId,
                productDto.created,
                productDto.orderId
            )
    }


    fun delete(productIds: List<UUID>) {
        jdbcTemplate
            .update(
                "DELETE FROM PUBLIC.PRODUCT WHERE P_ID IN ('${productIds.joinToString("', '")}')"
            )
    }
}