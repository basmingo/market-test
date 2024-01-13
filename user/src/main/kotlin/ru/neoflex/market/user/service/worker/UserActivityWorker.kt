package ru.neoflex.market.user.service.worker

import io.temporal.client.WorkflowClient
import io.temporal.client.WorkflowClientOptions
import io.temporal.serviceclient.WorkflowServiceStubs
import io.temporal.worker.WorkerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.neoflex.market.user.service.UserActivityImpl
import javax.annotation.PostConstruct

@Service
class UserActivityWorker {

    @Autowired
    lateinit var userActivity: UserActivityImpl

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
        val worker = workerFactory.newWorker("UserServiceActivity")
        worker.registerActivitiesImplementations(userActivity)
        workerFactory.start()
    }
}