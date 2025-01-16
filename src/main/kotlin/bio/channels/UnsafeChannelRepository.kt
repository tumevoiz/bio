package bio.channels

import bio.data.SQLConnector
import java.sql.Connection
import java.sql.Statement
import java.util.UUID

class UnsafeChannelRepository(
    connector: SQLConnector,
) : ChannelRepository {
    private val connection: Connection? = connector.retrieve();

    override fun getAll(): List<ChannelRow> {
        val channels = mutableListOf<ChannelRow>()
        connection ?: return channels
        val st = connection.createStatement();
        val rs = st.executeQuery("select id, name from channels");

        while (rs.next()) {
            channels.add(ChannelRow.fromResultSet(rs))
        }

        return channels
    }

    override fun create(row: ChannelRow): ChannelRow? {
        return connection?.let {
            val st = it.prepareStatement("insert into channels(id,name) values('${row.id}','${row.name}')");
            val affected = st.executeUpdate();

            if (affected > 0) {
                val st2 = it.createStatement();
                val rs = st2.executeQuery("select id, name from channels where id = '${row.id}'");
                if(rs.next()){
                    return ChannelRow.fromResultSet(rs)
                }
            }
            return null;
        };
    }

    override fun delete(id: UUID): Boolean {
        TODO("Not yet implemented")
    }

}

