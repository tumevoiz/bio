package bio.messages

import bio.prelude.Repository
import java.util.UUID

interface MessageRepository : Repository<UUID, MessageRow> {

    fun findAllMessages(): List<MessageRow>

    fun findMessageByUserId(userId: UUID): MessageRow?

    fun findMessageByChanelId(channelId: UUID): MessageRow?

    fun findMessageByContent(message: String): MessageRow?
}
