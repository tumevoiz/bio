package bio.messages

import bio.prelude.Repository
import java.util.UUID

interface MessageRepository : Repository<Int, MessageRow> {

    fun findAllMessages(): Collection<MessageRow>

    fun findMessageByUserId(userId: UUID): MessageRow?

    fun findMessageByChanelId(channelId: Int): MessageRow?

    fun findMessageByContent(message: String?): MessageRow?
}
