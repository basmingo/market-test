package ru.neoflex.market.order

import io.camunda.zeebe.spring.client.annotation.JobWorker
import io.camunda.zeebe.spring.client.annotation.Variable
import net.devh.boot.grpc.server.service.GrpcService
import org.springframework.stereotype.Service
import ru.neoflex.market.order.OrderServiceOuterClass.OrderRequest
import ru.neoflex.market.order.OrderServiceOuterClass.OrderResponse

@GrpcService
class OrderServiceImpl : OrderServiceGrpcKt.OrderServiceCoroutineImplBase() {

    override suspend fun getOrder(request: OrderRequest): OrderResponse {
        return OrderResponse
            .newBuilder()
            .apply { response = "${request.id} RESPONSE"}
            .build()
    }
}