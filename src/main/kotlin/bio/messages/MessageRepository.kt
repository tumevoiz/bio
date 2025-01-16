package bio.messages

import bio.prelude.Repository
import java.util.UUID

interface MessageRepository : Repository<UUID, MessageRow> {
    fun findMessagesByChannelId(channelId: UUID): List<MessageRow>
}
