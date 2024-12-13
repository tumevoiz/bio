package bio.messages

import java.util.UUID

data class MessageCreationRequest(
    val channelId: UUID,
    val userId: UUID,
    val message: String,
)
