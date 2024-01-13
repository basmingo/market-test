package ru.neoflex.market.order.service.worker

import io.temporal.client.WorkflowClient
import io.temporal.client.WorkflowClientOptions
import io.temporal.serviceclient.WorkflowServiceStubs
import io.temporal.worker.WorkerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.neoflex.market.order.service.OrderActivityImpl
import javax.annotation.PostConstruct

@Service
class OrderActivityWorker {

    @Autowired
    lateinit var orderActivity: OrderActivityImpl

    val client: WorkflowClient = WorkflowClient.newInstance(
        WorkflowServiceStubs
            .newLocalServiceStubs(),

        WorkflowClientOptions
            .newBuilder()
            .setNamespace("default")
            .build()
    )

    @PostConstruct
    fun workerStart() {
        val workerFactory = WorkerFactory.newInstance(client)
        val worker = workerFactory.newWorker("OrderServiceActivity")
        worker.registerActivitiesImplementations(orderActivity)
        workerFactory.start()
    }
}