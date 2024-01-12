package org.example.ru.neoflex.market.orchestrator.workflow

import io.temporal.client.ActivityCompletionFailureException
import io.temporal.workflow.Workflow
import org.example.ru.neoflex.market.orchestrator.output.port.OrderOutputPort
import org.example.ru.neoflex.market.orchestrator.output.port.ProductOutputPort
import org.example.ru.neoflex.market.orchestrator.output.port.UserOutputPort
import ru.neoflex.market.order.OrchestrationGrpcKt
import ru.neoflex.market.order.Orhcestration.*
import java.math.BigDecimal
import java.util.*

class CommitOrderImpl : CommitOrder, OrchestrationGrpcKt.OrchestrationCoroutineImplBase() {

    val orderService = Workflow.newActivityStub(OrderOutputPort::class.java)
    val userService = Workflow.newActivityStub(UserOutputPort::class.java)
    val productService = Workflow.newActivityStub(ProductOutputPort::class.java)

    override fun execute(userId: UUID, amount: BigDecimal) {
        val orderDetails = orderService.getOrderDetails(userId)
        val isBalanceEnough = userService.minusUserBalance(userId, amount)
        if (!isBalanceEnough) {
            userService.plusUserBalance(userId, amount)
        }

        orderService.setStatusToDelivery(orderDetails.orderId)
        try {
            productService.toDeliveryProduct(orderDetails.productIds)
        } catch (e: ActivityCompletionFailureException) {
            productService.onAvailableProduct(orderDetails.productIds)
            userService.plusUserBalance(userId, amount)
        }
    }

    override suspend fun commitOrder(request: CommitOrderRequest): CommitOrderResponse {
        execute(UUID.fromString(request.userId), BigDecimal(request.amount))
        return CommitOrderResponse.newBuilder().build()
    }
}