package ru.ushakov.beansjournal.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collection = "notifications")
data class Notification(
    @Id val id: String? = null,
    val userId: String = "",
    val type: String = "",
    val message: String = "",
    val createdAt: LocalDateTime = LocalDateTime.MIN
)