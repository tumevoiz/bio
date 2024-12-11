package bio.messages

import bio.data.SQLConnector
import java.util.UUID

class UnsafeMessageRepository (
    connector: SQLConnector,
) : MessageRepository {
    private val connection = connector.retrieve()

    override fun create(row: MessageRow): MessageRow? {
        return connection?.let {
            val stmt = it.createStatement()
            val rowsAffected = stmt.executeUpdate(createMessageUpdate(row))
            if (rowsAffected < 1) {
                throw MessageNotCreatedException()
            }

            val found = findMessageByUserId(row.userId)
            return found
        }
    }

    override fun delete(id: Int): Boolean {
        TODO("Not yet implemented")
    }

    private val createMessageUpdate = { row: MessageRow ->
        "insert into messages values ('${row.id}', '${row.userId}', '${row.channelId}', '${row.message}', '${row.sentAt}')"
    }

    override fun findAllMessages(): Collection<MessageRow> {
        TODO("Not yet implemented")
    }

    override fun findMessageByUserId(userId: UUID): MessageRow? {
        TODO("Not yet implemented")
    }

    override fun findMessageByChanelId(channelId: Int): MessageRow? {
        TODO("Not yet implemented")
    }

    override fun findMessageByContent(message: String?): MessageRow? {
        TODO("Not yet implemented")
    }
}
