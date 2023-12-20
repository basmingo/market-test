package ru.neoflex.market.api.gateway.controller.v1

import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import ru.neoflex.market.api.gateway.config.GrpcClient
import ru.neoflex.market.api.gateway.dto.ProductCreateRequestDto
import ru.neoflex.market.api.gateway.dto.ProductRequest
import ru.neoflex.market.warehouse.WarehouseServiceOuterClass.*
import java.util.*

@RestController
@RequestMapping("/products")
class WarehouseRouter(private val grpcClient: GrpcClient) {

    @PutMapping
    fun book(
        @RequestBody bookProductRequest: Mono<ProductRequest>,
        @RequestParam("s") status: String
    ): Mono<UUID> {
        return when (status) {
            "book" -> bookProductRequest
                .map {
                    grpcClient
                        .getWarehouseServiceClient()
                        .bookProduct(
                            ProductBookRequest
                                .newBuilder()
                                .apply {
                                    productId = "${it.productId}"
                                    userId = "${it.userId}"
                                }
                                .build()
                        )
                }
                .map { UUID.fromString(it.productId) }

            "unbook" -> bookProductRequest
                .map {
                    grpcClient
                        .getWarehouseServiceClient()
                        .unbookProduct(
                            ProductUnbookRequest
                                .newBuilder()
                                .apply {
                                    productId = "${it.productId}"
                                    userId = "${it.userId}"
                                }
                                .build()
                        )
                }
                .map { UUID.fromString(it.productId) }

            else -> throw RuntimeException("!")
        }
    }

    @PostMapping
    fun create(@RequestBody displayName: Mono<ProductCreateRequestDto>): Mono<UUID> =
        displayName
            .map {
                grpcClient
                    .getWarehouseServiceClient()
                    .createProduct(
                        ProductCreateRequest
                            .newBuilder()
                            .apply {
                                this.displayName = it.displayName
                            }
                            .build()
                    )
            }
            .map {
                UUID.fromString(it.productId)
            }

    @DeleteMapping
    fun delete(@RequestParam("id") deleteId: UUID): Mono<UUID> =
        Mono.just(deleteId)
            .map {
                grpcClient
                    .getWarehouseServiceClient()
                    .deleteProduct(
                        ProductDeleteRequest
                            .newBuilder()
                            .apply {
                                productId = "$it"
                            }
                            .build()
                    )
            }
            .map {
                UUID.fromString(it.productId)
            }
}