package ru.ushakov.beansjournal.controller

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.ushakov.beansjournal.repository.NotificationRepository
import java.time.LocalDateTime


@RestController
@RequestMapping("/journal")
class NotificationController(
    private val notificationRepository: NotificationRepository
) {

    @GetMapping("/{userId}")
    fun getNotifications(@PathVariable userId: String): List<NotificationResponse> {
        return notificationRepository.findAllByUserIdOrderByCreatedAtDesc(userId).map {
            NotificationResponse(
                id = it.id,
                type = it.type,
                message = it.message,
                createdAt = it.createdAt
            )
        }
    }
}

data class NotificationResponse(
    val id: String?,
    val type: String = "",
    val message: String = "",
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val createdAt: LocalDateTime = LocalDateTime.MIN
)