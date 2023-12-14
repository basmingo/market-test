package ru.neoflex.market.api.gateway.controller

import io.grpc.ManagedChannelBuilder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import ru.neoflex.market.order.UserServiceGrpc
import ru.neoflex.market.order.UserServiceGrpcKt
import ru.neoflex.market.order.UserServiceOuterClass

@RestController("orders/")
class OrdersRouter {
}