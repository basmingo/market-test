package ru.neoflex.market.api.gateway.controller

import kotlinx.coroutines.flow.map
import kotlinx.coroutines.reactor.asFlux
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import ru.neoflex.market.api.gateway.config.GrpcClient
import ru.neoflex.market.api.gateway.dto.BalanceDownRequestDto
import ru.neoflex.market.api.gateway.dto.BalanceUpRequestDto
import ru.neoflex.market.api.gateway.dto.UserCreateDto
import ru.neoflex.market.api.gateway.dto.UserDto
import ru.neoflex.market.order.UserServiceOuterClass.*
import java.math.BigDecimal
import java.util.UUID

@RestController
@RequestMapping("/users")
class UserRouter(private val grpcClient: GrpcClient) {

    @PostMapping("/create")
    fun usersCreate(@RequestBody userCreateDto: Mono<UserCreateDto>): Mono<String> =
        userCreateDto
            .map {
                grpcClient
                    .getUserServiceClient()
                    .userCreate(
                        UserCreateRequest
                            .newBuilder()
                            .apply {
                                name = it.name
                                lastName = it.lastName
                                balance = it.balance
                                age = it.age
                            }
                            .build()
                    )
            }
            .map { it.userId }

    @PostMapping("/delete")
    fun userDelete(@RequestBody deleteUserId: Mono<UUID>): Mono<String> =
        deleteUserId
            .map {
                grpcClient
                    .getUserServiceClient()
                    .userDelete(
                        UserDeleteRequest
                            .newBuilder()
                            .apply {
                                userId = "$it"
                            }
                            .build()
                    )
            }
            .map { it.userId }

    @PostMapping("/balance/up")
    fun balanceUp(@RequestBody balanceUpRequestDto: Mono<BalanceUpRequestDto>): Mono<String> =
        balanceUpRequestDto
            .map {
                grpcClient
                    .getUserServiceClient()
                    .balanceUp(
                        BalanceUpRequest
                            .newBuilder()
                            .apply {
                                userId = it.userId
                                amount = it.amount
                            }
                            .build()
                    )
            }
            .map { it.userId }

    @PostMapping("/balance/down")
    fun balanceDown(@RequestBody balanceDownRequest: Mono<BalanceDownRequestDto>): Mono<String> =
        balanceDownRequest
            .map {
                grpcClient
                    .getUserServiceClient()
                    .balanceMinus(
                        BalanceMinusRequest
                            .newBuilder()
                            .apply {
                                userId = it.userId
                                amount = it.amount
                            }
                            .build()
                    )
            }
            .map { it.userId }

    @GetMapping("/get/all")
    fun getUsers(): Flux<UserDto> {
        return grpcClient
            .getUserServiceClientCoroutines()
            .getUsers(Empty.newBuilder().build())
            .map {
                UserDto(
                    UUID.fromString(it.userId),
                    it.name,
                    it.lastName,
                    BigDecimal(it.balance),
                    it.age.toInt()
                )
            }
            .asFlux()
    }

    @GetMapping("/get")
    fun getUser(@RequestParam("user_id") userId: UUID): Mono<UserDto> =
        Mono.just(userId)
            .map {
                grpcClient
                    .getUserServiceClient()
                    .getUser(UserRequest.newBuilder().apply { this.userId = "$userId" }.build())
            }
            .map {
                UserDto(
                    UUID.fromString(it.userId),
                    it.name,
                    it.lastName,
                    BigDecimal(it.balance),
                    it.age.toInt()
                )
            }
}
