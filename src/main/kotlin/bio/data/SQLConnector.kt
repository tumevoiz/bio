package bio.data

import java.sql.Connection
import java.sql.DriverManager

class SQLConnector(private val sqlConfig: SQLConfig?) {
    private var _connection: Connection? = null

    fun retrieve(): Connection? {
        if (_connection == null) {
            sqlConfig?.let {
                _connection = DriverManager.getConnection(
                    it.url,
                    it.username,
                    it.password,
                )
            }
        }

        return _connection
    }
}
