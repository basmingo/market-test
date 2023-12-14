package ru.neoflex.market.api.gateway.config

import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RouterParams {

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

    @Bean
    fun userServiceChannel(): ManagedChannel = ManagedChannelBuilder
        .forAddress(userHost, userGrpcPort.toInt())
        .usePlaintext()
        .build()

    @Bean
    fun orderServiceChannel(): ManagedChannel = ManagedChannelBuilder
        .forAddress(orderHost, orderGrpcPort.toInt())
        .usePlaintext()
        .build()

    @Bean
    fun warehouseServiceChannel(): ManagedChannel = ManagedChannelBuilder
        .forAddress(warehouseHost, warehouseGrpcPort.toInt())
        .usePlaintext()
        .build()
}