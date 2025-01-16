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

            return row
        }
    }

    override fun delete(id: UUID): Boolean {
        TODO("Not yet implemented")
    }

    private val createMessageUpdate = { row: MessageRow ->
        "insert into messages values ('${row.id}', '${row.userId}', '${row.channelId}', '${row.message}', '${row.sentAt}')"
    }

    override fun findMessagesByChannelId(channelId: UUID): List<MessageRow> {
        val messages = mutableListOf<MessageRow>()
        connection ?: return messages

        val stmt = connection.createStatement()
        val rs = stmt.executeQuery(findMessagesByChannelIdQuery(channelId))
        while (rs.next()) {
            messages.add(MessageRow.fromResultSet(rs))
        }

        return messages.toList()
    }

    private val findMessagesByChannelIdQuery = { channelId: UUID ->
        "select * from messages where channel_id='${channelId}'"
    }
}
