package ru.neoflex.market.order

import io.camunda.zeebe.client.ZeebeClient
import io.camunda.zeebe.client.api.response.ActivatedJob
import io.camunda.zeebe.client.api.worker.JobClient
import io.camunda.zeebe.client.api.worker.JobHandler
import io.camunda.zeebe.spring.client.annotation.JobWorker
import org.springframework.stereotype.Service

@Service
class OrderService {

    @JobWorker(type = "ors")
    fun fWorker() {
        println("WAaAaAaYayaYa")
    }
}