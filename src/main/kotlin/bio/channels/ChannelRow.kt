package bio.channels

import bio.prelude.RowClass
import java.sql.ResultSet
import java.util.UUID

data class ChannelRow(
    val id: Int,
    val name: String,
) : RowClass<Int> {
    override fun id(): Int = id;

    override fun tableName(): String = "channels";

    companion object {
        fun fromResultSet(rs: ResultSet): ChannelRow =
            ChannelRow(
                id = rs.getInt("id"),
                name = rs.getString("name")
            )
    }
}
