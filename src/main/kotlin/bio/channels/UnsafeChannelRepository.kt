package bio.channels

import bio.data.SQLConnector
import java.sql.Connection
import java.sql.Statement

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
            val st = it.prepareStatement("insert into channels(name) values('${row.name}')", Statement.RETURN_GENERATED_KEYS);
            val affected = st.executeUpdate();

            if (affected > 0) {
                val st2 = it.createStatement();
                if (st.generatedKeys.next()) {
                    val insertedId = st.generatedKeys.getInt("id");
                    val rs = st2.executeQuery("select id, name from channels where id = $insertedId");
                    if(rs.next()){
                        return ChannelRow.fromResultSet(rs)
                    }
                }
            }
            return null;
        };
    }

    override fun delete(id: Int): Boolean {
        TODO("Not yet implemented")
    }

}

