package ru.neoflex.market.orchestrator.workflow

import io.temporal.activity.ActivityOptions
import io.temporal.failure.TemporalFailure
import io.temporal.workflow.Saga
import io.temporal.workflow.Workflow
import ru.neoflex.market.orchestrator.output.port.OrderOutputPort
import ru.neoflex.market.orchestrator.output.port.ProductOutputPort
import ru.neoflex.market.orchestrator.output.port.UserOutputPort
import java.math.BigDecimal
import java.time.Duration
import java.util.*

class CommitOrderImpl : CommitOrder {

    private val saga = Saga(Saga.Options.Builder().build())
    private val orderService: OrderOutputPort = Workflow.newActivityStub(
        OrderOutputPort::class.java,
        ActivityOptions
            .newBuilder()
            .setTaskQueue("OrderServiceActivity")
            .setStartToCloseTimeout(Duration.ofSeconds(5))
            .build()
    )

    private val userService: UserOutputPort = Workflow.newActivityStub(
        UserOutputPort::class.java,
        ActivityOptions
            .newBuilder()
            .setTaskQueue("UserServiceActivity")
            .setStartToCloseTimeout(Duration.ofSeconds(5))
            .build()
        )
    private val productService: ProductOutputPort = Workflow.newActivityStub(
        ProductOutputPort::class.java,
        ActivityOptions
            .newBuilder()
            .setTaskQueue("ProductServiceActivity")
            .setStartToCloseTimeout(Duration.ofSeconds(5))
            .build()
    )

    override fun execute(userId: UUID, amount: BigDecimal) {
        try {
            val orderDetails = orderService.getOrderDetails(userId)

            saga.addCompensation(userService::plusUserBalance, userId, amount)
            userService.minusUserBalance(userId, amount)

            saga.addCompensation(productService::onAvailableProduct, orderDetails.productIds)
            orderService.setStatusToDelivery(orderDetails.orderId)

        } catch (e: TemporalFailure) {
            saga.compensate()
            throw e
        }

    }
}