package ru.neoflex.market.warehouse.service

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import net.devh.boot.grpc.server.service.GrpcService
import ru.neoflex.market.warehouse.WarehouseServiceGrpcKt
import ru.neoflex.market.warehouse.WarehouseServiceOuterClass.*
import ru.neoflex.market.warehouse.repository.ProductDao
import ru.neoflex.market.warehouse.service.dto.BookedEventDto
import ru.neoflex.market.warehouse.service.dto.ProductDto
import java.time.LocalDateTime
import java.util.*

@GrpcService
class WarehouseServiceImpl(
    private val productDao: ProductDao,
    private val productMessageProducer: ProductMessageProducer
) :
    WarehouseServiceGrpcKt.WarehouseServiceCoroutineImplBase() {

    override suspend fun createProduct(request: ProductCreateRequest): ProductCreateResponse {
        val currentProductId = UUID.randomUUID()
        productDao.create(
            ProductDto(
                productId = currentProductId,
                displayName = request.displayName,
                created = LocalDateTime.now(),
                updated = LocalDateTime.now(),
                status = "AVAILABLE"
            )
        )

        return ProductCreateResponse
            .newBuilder()
            .apply { productId = "$currentProductId" }
            .build()
    }

    override suspend fun bookProduct(request: ProductBookRequest): ProductBookResponse {
        val currentProductId = UUID.fromString(request.productId)
        productDao.update(
            productIds = listOf(currentProductId),
            status = "BOOKED"
        )

        productMessageProducer.productBooked(
            BookedEventDto(
                userId = UUID.fromString(request.userId),
                productId = currentProductId
            )
        )

        return ProductBookResponse
            .newBuilder()
            .apply { productId = request.productId }
            .build()
    }

    override suspend fun unbookProduct(request: ProductUnbookRequest): ProductUnbookResponse {
        val currentProductId = UUID.fromString(request.productId)
        productDao.update(
            productIds = listOf(currentProductId),
            status = "AVAILABLE"
        )

        productMessageProducer.productBooked(
            BookedEventDto(
                userId = UUID.fromString(request.userId),
                productId = currentProductId
            )
        )

        return ProductUnbookResponse
            .newBuilder()
            .apply { productId = request.productId }
            .build()
    }

    override suspend fun deleteProduct(request: ProductDeleteRequest): ProductDeleteResponse {
        productDao.delete(
            productId = UUID.fromString(request.productId)
        )

        return ProductDeleteResponse
            .newBuilder()
            .apply { productId = request.productId }
            .build()
    }

    override fun getProducts(request: Empty): Flow<Product> =
        productDao
            .getAll()
            .map {
                Product.newBuilder()
                    .apply {
                        productId = "${it.productId}"
                        displayName = it.displayName
                        created = "${it.created}"
                        updated = "${it.updated}"
                        status = it.status
                    }
                    .build()
            }
            .asFlow()

    fun getById(productId: UUID): ProductDto? = productDao.getById(productId)
}