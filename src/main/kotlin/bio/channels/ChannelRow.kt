package bio.channels

import bio.prelude.RowClass
import java.sql.ResultSet
import java.util.UUID

data class ChannelRow(
    val id: UUID,
    val name: String,
) : RowClass<UUID> {
    override fun id(): UUID = id;

    override fun tableName(): String = "channels";

    companion object {
        fun fromResultSet(rs: ResultSet): ChannelRow =
            ChannelRow(
                id = UUID.fromString(rs.getString("id")),
                name = rs.getString("name")
            )
    }
}
