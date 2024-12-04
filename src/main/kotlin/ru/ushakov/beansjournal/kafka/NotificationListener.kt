package ru.ushakov.beansjournal.kafka

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import ru.ushakov.beansjournal.domain.*
import ru.ushakov.beansjournal.repository.NotificationRepository
import java.time.LocalDateTime

@Service
class NotificationListener(
    private val objectMapper: ObjectMapper,
    private val notificationRepository: NotificationRepository
) {

    @KafkaListener(topics = ["ProfileCreated"])
    fun handleProfileCreated(message: String) {
        val event = objectMapper.readValue(message, ProfileCreatedEvent::class.java)
        val notification = Notification(
            userId = event.profile.id.toString(),
            type = "WELCOME",
            message = "Добро пожаловать в Java Beans! Но и про работу тоже не забывайте.",
            createdAt = LocalDateTime.now()
        )
        notificationRepository.save(notification)
    }

    @KafkaListener(topics = ["OrderCreated"])
    fun handleOrderCreated(message: String) {
        val event = objectMapper.readValue(message, OrderCreatedEvent::class.java)
        val notification = Notification(
            userId = event.userId,
            type = "ORDER_CREATED",
            message = "Ваш заказ №${event.orderId} находится в обработке.",
            createdAt = LocalDateTime.now()
        )
        notificationRepository.save(notification)
    }

    @KafkaListener(topics = ["OrderUpdated"])
    fun handleOrderUpdated(message: String) {
        val event = objectMapper.readValue(message, OrderUpdatedEvent::class.java)
        val notification = Notification(
            userId = event.userId,
            type = "ORDER_UPDATED",
            message = "Статус вашего заказа №${event.orderId} изменен на ${event.newStatus}.",
            createdAt = LocalDateTime.now()
        )
        notificationRepository.save(notification)
    }

    @KafkaListener(topics = ["LoyaltyUpdated"])
    fun handleLoyaltyUpdated(message: String) {
        val event = objectMapper.readValue(message, LoyaltyUpdateEvent::class.java)
        val action = if (event.loyaltyEventType == LoyaltyEventType.POINTS_ADDED) "Вам начислены" else "У вас списаны"
        val notification = Notification(
            userId = event.userId,
            type = "LOYALTY_UPDATED",
            message = "$action бонусы: ${event.pointsAmount}.",
            createdAt = LocalDateTime.now()
        )
        notificationRepository.save(notification)
    }
}