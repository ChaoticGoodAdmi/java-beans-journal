package ru.ushakov.beansjournal.repository

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import ru.ushakov.beansjournal.domain.Notification

@Repository
interface NotificationRepository : MongoRepository<Notification, String> {
    fun findAllByUserIdOrderByCreatedAtDesc(userId: String): List<Notification>
}