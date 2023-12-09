package ru.neoflex.market.order

import io.camunda.zeebe.spring.client.annotation.JobWorker
import io.camunda.zeebe.spring.client.annotation.Variable
import io.grpc.stub.StreamObserver
import org.springframework.stereotype.Service
import ru.neoflex.market.order.OrderServiceOuterClass.OrderRequest
import ru.neoflex.market.order.OrderServiceOuterClass.OrderResponse

@Service
class OrderServiceImpl : OrderServiceGrpc.OrderServiceImplBase() {

    @JobWorker(type = "ors")
    fun fWorker(@Variable input: String): Map<String, Any> {
        println(input)
        return mapOf("out" to input + "TEST")
    }

    @JobWorker(type = "rrs")
    fun rWorker() {
        println("r end")
    }

    @JobWorker(type = "lrs")
    fun lWorker() {
        println("l end")
    }
}