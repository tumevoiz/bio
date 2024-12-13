package bio.messages

import java.time.OffsetDateTime
import java.util.UUID

class MessageService(
    private val messageRepository: MessageRepository,
) {
    fun createMessage(request: MessageCreationRequest): Message {
        // TODO: userId as a parameter
        val messageRow =
            MessageRow(
                id = UUID.randomUUID(),
                userId = request.userId,
                channelId = request.channelId,
                message = request.message,
                sentAt = OffsetDateTime.now(),
            )

        val createdMessageRow = messageRepository.create(messageRow)
        return createdMessageRow?.toDomainObject()
            ?: throw MessageNotCreatedException()
    }
}
