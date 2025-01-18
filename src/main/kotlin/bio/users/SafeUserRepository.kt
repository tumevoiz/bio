package bio.users

import bio.Security
import bio.data.SQLConnector
import java.util.UUID

class SafeUserRepository(
    connector: SQLConnector,
) : UserRepository {
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

    override fun findByUUID(uuid: UUID): UserRow? {
        return connection?.let {
            val stmt = it.createStatement()
            val rs = stmt.executeQuery(findByUUIDQuery(uuid))
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
        "select * from users WHERE username='${Security.sanitazeSql(username)}'"
    }

    private val findByUUIDQuery = { uuid: UUID ->
        "select * from users WHERE id='${uuid.toString()}'"
    }


    private val createUserUpdate = { row: UserRow ->
        "insert into users values ('${row.id}', '${row.username}', '${row.password}')"
    }
}
