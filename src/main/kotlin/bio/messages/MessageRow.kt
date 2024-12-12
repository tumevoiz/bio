package bio.messages

import bio.prelude.RowClass
import java.sql.ResultSet
import java.time.OffsetDateTime
import java.util.UUID

data class MessageRow(
    val id: UUID,
    val userId: UUID,
    val channelId: UUID,
    val message: String,
    val sentAt: OffsetDateTime,
) : RowClass<UUID> {
    override fun id(): UUID = id

    override fun tableName(): String = "messages"

    companion object {
        fun fromResultSet(rs: ResultSet): MessageRow =
            MessageRow(
                id = UUID.fromString(rs.getString("id")),
                userId = UUID.fromString(rs.getString("user_id")),
                channelId = UUID.fromString(rs.getString("channel_id")),
                message = rs.getString("message"),
                sentAt = rs.getObject("sent_at", OffsetDateTime::class.java),
            )
    }
}
