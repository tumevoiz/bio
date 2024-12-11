package bio.messages

import bio.prelude.RowClass
import java.sql.ResultSet
import java.time.OffsetDateTime
import java.util.UUID

data class MessageRow(
    val id: Int,
    val userId: UUID,
    val channelId: Int,
    val message: String,
    val sentAt: OffsetDateTime,
) : RowClass<Int> {
    override fun id(): Int = id

    override fun tableName(): String = "messages"

    companion object {
        fun fromResultSet(rs: ResultSet): MessageRow =
            MessageRow(
                id = rs.getInt("id"),
                userId = UUID.fromString(rs.getString("user_id")),
                channelId = rs.getInt("channel_id"),
                message = rs.getString("message"),
                sentAt = rs.getObject("sent_at", OffsetDateTime::class.java)
            )
    }
}
