package bio.messages

import java.time.OffsetDateTime
import java.util.UUID

class MessageService(
    private val messageRepository: MessageRepository,
) {
    fun createMessage(request: MessageCreationRequest, userUUID: UUID): Message {
        val messageRow =
            MessageRow(
                id = UUID.randomUUID(),
                userId = userUUID,
                channelId = request.channelId,
                message = request.message,
                sentAt = OffsetDateTime.now(),
            )

        val createdMessageRow = messageRepository.create(messageRow)
        return createdMessageRow?.toDomainObject()
            ?: throw MessageNotCreatedException()
    }

    fun getChannelMessages(channelUUID: UUID): List<Message> {
        val messages = messageRepository.findMessagesByChannelId(channelUUID)
        return messages.map { it.toDomainObject() }
    }
}
