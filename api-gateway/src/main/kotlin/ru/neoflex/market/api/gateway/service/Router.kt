package ru.neoflex.market.api.gateway.service

import io.grpc.ManagedChannelBuilder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import ru.neoflex.market.order.UserServiceGrpc
import ru.neoflex.market.order.UserServiceGrpcKt
import ru.neoflex.market.order.UserServiceOuterClass

@RestController
class Router : UserServiceGrpcKt.UserServiceCoroutineImplBase() {

    val userServiceChannel = ManagedChannelBuilder
        .forAddress("localhost", 9093)
        .usePlaintext()
        .build()

    @GetMapping("/users/create")
    fun t() {
        println("START")
        val a = UserServiceGrpc.newBlockingStub(userServiceChannel)
            .userCreate(
                UserServiceOuterClass.UserCreateRequest
                .newBuilder()
                .apply {
                    name = "Pablo"
                    lastName = "Basmanov"
                    balance = "1000000"
                    age = 32
                }
                .build())
        println(a)
    }
}