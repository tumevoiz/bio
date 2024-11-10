package bio.users

import bio.data.SQLConnector
import java.util.UUID

/**
 * Unsafe user repository implementation that contains the potential vulnerabilities like SQL injection.
 */
class UnsafeUserRepository(connector: SQLConnector) : UserRepository {
    private val connection = connector.retrieve()

    override fun findByUsername(username: String): UserRow? {
        return connection?.let {
            val stmt = it.createStatement()
            val rs = stmt.executeQuery(findByUsernameQuery(username))
            if (!rs.next()) {
                return null
            }
            return UserRow.fromResultSet(rs)
        }
    }

    override fun create(row: UserRow): UserRow? {
        return connection?.let {
            val stmt = it.createStatement()
            val rowsAffected = stmt.executeUpdate(createUserUpdate(row))
            if (rowsAffected < 1) {
                throw UserNotCreatedException()
            }

            val found = findByUsername(row.username)
            return found
        }
    }

    override fun delete(id: UUID): Boolean {
        TODO("Not yet implemented")
    }

    private val findByUsernameQuery = { username: String ->
        "select * from users WHERE username='${username}'"
    }

    private val createUserUpdate = { row: UserRow ->
        "insert into users values ('${row.id}', '${row.username}', '${row.password}')"
    }
}
