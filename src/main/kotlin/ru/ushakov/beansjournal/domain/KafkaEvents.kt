package ru.ushakov.beansjournal.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.math.BigDecimal
import java.time.LocalDateTime

data class ProfileCreatedEvent(
    val profile: Profile
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Profile(
    val id: Long = 0
)

data class OrderCreatedEvent(
    val orderId: Long,
    val userId: String,
    val coffeeShopId: String,
    val items: List<OrderItem>,
    val totalCost: BigDecimal,
    val createdAt: LocalDateTime,
    val status: OrderStatus,
    val bonusPointsUsed: Int
)

data class OrderItem(
    val productId: String,
    val quantity: Int,
    val price: BigDecimal
)

data class OrderUpdatedEvent(
    val orderId: Long,
    val userId: String,
    val newStatus: OrderStatus,
)

data class LoyaltyUpdateEvent(
    val userId: String,
    val pointsAmount: Int,
    val loyaltyEventType: LoyaltyEventType
)

enum class OrderStatus { CREATED, IN_PROGRESS, READY, DELIVERED }
enum class LoyaltyEventType { POINTS_ADDED, POINTS_USED }