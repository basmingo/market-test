package ru.neoflex.market.api.gateway.controller

import io.grpc.ManagedChannelBuilder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import ru.neoflex.market.api.gateway.config.RouterParams

@RestController
class UserRouter(private val routerParams: RouterParams) {

    @GetMapping("users/create")
    fun usersCreate() {
    }
}