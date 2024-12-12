package bio.messages

import bio.data.SQLConnector
import java.util.UUID

class UnsafeMessageRepository(
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

    override fun delete(id: UUID): Boolean {
        TODO("Not yet implemented")
    }

    private val createMessageUpdate = { row: MessageRow ->
        "insert into messages values ('${row.id}', '${row.userId}', '${row.channelId}', '${row.message}', '${row.sentAt}')"
    }

    override fun findAllMessages(): List<MessageRow> {
        TODO("Not yet implemented")
    }

    override fun findMessageByUserId(userId: UUID): MessageRow? {
        TODO("Not yet implemented")
    }

    override fun findMessageByChanelId(channelId: UUID): MessageRow? {
        TODO("Not yet implemented")
    }

    override fun findMessageByContent(message: String): MessageRow? {
        TODO("Not yet implemented")
    }

}
