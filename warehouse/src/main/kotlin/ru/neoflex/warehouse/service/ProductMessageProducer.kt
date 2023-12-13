package ru.neoflex.warehouse.service

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import ru.neoflex.warehouse.service.dto.BookedEventDto

@Service
class ProductMessageProducer(private val productBookedProducer: KafkaTemplate<String, BookedEventDto>) {

    fun productBooked(message: BookedEventDto) {
        productBookedProducer.send("product_booked_event", message)
    }
}