package ru.neoflex.market.order.repository

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service
import ru.neoflex.market.order.service.dto.ProductDto
import java.util.*

@Service
class ProductDao(val jdbcTemplate: JdbcTemplate) {

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