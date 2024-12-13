package bio.channels

import bio.data.SQLConnector
import java.sql.Connection
import java.sql.Statement
import java.util.UUID

class UnsafeChannelRepository(
    connector: SQLConnector,
) : ChannelRepository {
    private val connection: Connection? = connector.retrieve();

    override fun getAll(): Collection<ChannelRow> {
        return connection?.let {
            val result = emptyList<ChannelRow>().toMutableList();
            val st = it.createStatement();
            val rs = st.executeQuery("select id, name from channels");

            while (rs.next()) {
                result += ChannelRow.fromResultSet(rs)
            }
            return result;
        } ?: emptyList<ChannelRow>();
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

