package bio.messages

import java.time.OffsetDateTime
import java.util.UUID

data class Message(
    val userId: UUID,
    val channelId: UUID,
    val message: String,
    val sentAt: OffsetDateTime,
)

fun MessageRow.toDomainObject(): Message =
    Message(
        userId = this.userId,
        channelId = this.channelId,
        message = this.message,
        sentAt = this.sentAt,
    )
