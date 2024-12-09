package bio.data

import java.sql.Connection
import java.sql.DriverManager

class SQLConnector(
    private val sqlConfig: SQLConfig?,
) {
    private var connection: Connection? = null

    fun retrieve(): Connection? {
        if (connection == null) {
            sqlConfig?.let {
                connection =
                    DriverManager.getConnection(
                        it.url,
                        it.username,
                        it.password,
                    )
            }
        }

        return connection
    }
}
