package bio.messages

import java.time.OffsetDateTime
import java.util.UUID

class MessageService(
    private val messageRepository: MessageRepository,
) {
    fun createMessage(request: MessageCreationRequest): Message {
        val messageRow =
            MessageRow(
                id = request.id,
                userId = request.userId,
                channelId = request.chanelId,
                message = request.message,
                sentAt = request.sentAt,
            )

        val createdMessageRow = messageRepository.create(messageRow)
        return createdMessageRow?.toDomainObject()
            ?: throw MessageNotCreatedException()
    }
}
