package ru.neoflex.market.order.service.worker

import io.temporal.client.WorkflowClient
import io.temporal.client.WorkflowClientOptions
import io.temporal.serviceclient.WorkflowServiceStubs
import io.temporal.serviceclient.WorkflowServiceStubsOptions
import io.temporal.worker.WorkerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import ru.neoflex.market.order.service.OrderActivityImpl
import javax.annotation.PostConstruct

@Service
class OrderActivityWorker {

    @Value("\${temporal.host}")
    lateinit var temporalHost: String

    @Value("\${temporal.port}")
    lateinit var temporalPort: String

    @Autowired
    lateinit var orderActivity: OrderActivityImpl

    @PostConstruct
    fun workerStart() {
        val options: WorkflowServiceStubsOptions = WorkflowServiceStubsOptions
            .newBuilder()
            .setTarget("$temporalHost:$temporalPort")
            .build()

        val clientOptions: WorkflowClientOptions = WorkflowClientOptions
            .newBuilder()
            .setNamespace("default")
            .build()

        val client: WorkflowClient =
            WorkflowClient.newInstance(WorkflowServiceStubs.newServiceStubs(options), clientOptions)
        val workerFactory = WorkerFactory.newInstance(client)
        val worker = workerFactory.newWorker("OrderServiceActivity")
        worker.registerActivitiesImplementations(orderActivity)
        workerFactory.start()
    }
}