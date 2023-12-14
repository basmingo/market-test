package ru.neoflex.market.user.service

import net.devh.boot.grpc.server.service.GrpcService
import ru.neoflex.market.order.UserServiceGrpcKt
import ru.neoflex.market.order.UserServiceOuterClass.*
import ru.neoflex.market.user.repository.UserDao
import ru.neoflex.market.user.service.dto.UserDto
import java.math.BigDecimal
import java.util.UUID

@GrpcService
class UserServiceImpl(private val userDao: UserDao) : UserServiceGrpcKt.UserServiceCoroutineImplBase() {

    override suspend fun userCreate(request: UserCreateRequest): UserCreateResponse {
        val newUserId = UUID.randomUUID()
        userDao.create(
            UserDto(
                newUserId,
                request.name,
                request.lastName,
                BigDecimal(request.balance),
                request.age
            )
        )
        return UserCreateResponse
            .newBuilder()
            .apply { userId = "$newUserId" }
            .build()
    }

    override suspend fun userDelete(request: UserDeleteRequest): UserDeleteResponse {
        userDao.delete(UUID.fromString(request.userId))
        return UserDeleteResponse
            .newBuilder()
            .apply { userId = request.userId }
            .build()
    }

    override suspend fun balanceUp(request: BalanceUpRequest): BalanceUpResponse {
        val amount = (BigDecimal(request.amount))
        userDao.plusBalance(UUID.fromString(request.userId), amount)

        return BalanceUpResponse
            .newBuilder()
            .apply { userId = request.userId }
            .build()
    }

    override suspend fun balanceMinus(request: BalanceMinusRequest): BalanceMinusResponse {
        val amount = (BigDecimal(request.amount))
        userDao.minusBalance(UUID.fromString(request.userId), amount)

        return BalanceMinusResponse
            .newBuilder()
            .apply { userId = request.userId }
            .build()
    }

    fun getUser(userId: UUID): UserDto? =
        userDao.getById(userId)
}