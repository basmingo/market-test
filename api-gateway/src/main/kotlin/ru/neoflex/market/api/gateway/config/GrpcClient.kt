package ru.neoflex.market.api.gateway.config

import io.grpc.ManagedChannelBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.neoflex.market.order.OrchestratorServiceGrpc.*
import ru.neoflex.market.order.OrderServiceGrpc
import ru.neoflex.market.order.OrderServiceGrpc.OrderServiceBlockingStub
import ru.neoflex.market.order.UserServiceGrpc
import ru.neoflex.market.order.UserServiceGrpc.UserServiceBlockingStub
import ru.neoflex.market.order.UserServiceGrpcKt.UserServiceCoroutineStub
import ru.neoflex.market.warehouse.WarehouseServiceGrpc
import ru.neoflex.market.warehouse.WarehouseServiceGrpc.WarehouseServiceBlockingStub
import ru.neoflex.market.warehouse.WarehouseServiceGrpcKt.WarehouseServiceCoroutineStub

@Configuration
class GrpcClient {

    @Value("\${routers.orders.host}")
    lateinit var orderHost: String

    @Value("\${routers.orders.grpc-port}")
    lateinit var orderGrpcPort: String

    @Value("\${routers.warehouse.host}")
    lateinit var warehouseHost: String

    @Value("\${routers.warehouse.grpc-port}")
    lateinit var warehouseGrpcPort: String

    @Value("\${routers.users.host}")
    lateinit var userHost: String

    @Value("\${routers.users.grpc-port}")
    lateinit var userGrpcPort: String

    @Value("\${routers.orchestrator.host}")
    lateinit var orchestrationHost: String

    @Value("\${routers.orchestrator.grpc-port}")
    lateinit var orchestrationGrpcPort: String

    @Bean
    fun getUserServiceClient(): UserServiceBlockingStub =
        UserServiceGrpc
            .newBlockingStub(
                ManagedChannelBuilder
                    .forAddress(userHost, userGrpcPort.toInt())
                    .usePlaintext()
                    .build()
            )

    @Bean
    fun getUserServiceClientCoroutines(): UserServiceCoroutineStub =
        UserServiceCoroutineStub(
            ManagedChannelBuilder
                .forAddress(userHost, userGrpcPort.toInt())
                .usePlaintext()
                .build()
        )

    @Bean
    fun getOrderServiceClient(): OrderServiceBlockingStub =
        OrderServiceGrpc
            .newBlockingStub(
                ManagedChannelBuilder
                    .forAddress(orderHost, orderGrpcPort.toInt())
                    .usePlaintext()
                    .build()
            )

    @Bean
    fun getWarehouseServiceClient(): WarehouseServiceBlockingStub =
        WarehouseServiceGrpc
            .newBlockingStub(
                ManagedChannelBuilder
                    .forAddress(warehouseHost, warehouseGrpcPort.toInt())
                    .usePlaintext()
                    .build()
            )

    @Bean
    fun getWarehouseServiceClientCoroutines(): WarehouseServiceCoroutineStub =
        WarehouseServiceCoroutineStub(
                ManagedChannelBuilder
                    .forAddress(warehouseHost, warehouseGrpcPort.toInt())
                    .usePlaintext()
                    .build()
            )

    @Bean
    fun getOrchestratorServiceClient(): OrchestratorServiceBlockingStub =
        newBlockingStub(
                ManagedChannelBuilder
                    .forAddress(orchestrationHost, orchestrationGrpcPort.toInt())
                    .usePlaintext()
                    .build()
            )
}