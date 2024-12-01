package bio.users

import bio.prelude.RowClass
import java.sql.ResultSet
import java.util.UUID

data class UserRow(
    val id: UUID,
    val username: String,
    val password: HashedPassword,
) : RowClass<UUID> {
    override fun id(): UUID = id

    override fun tableName(): String = "users"

    companion object {
        fun fromResultSet(rs: ResultSet): UserRow =
            UserRow(
                id = UUID.fromString(rs.getString("id")),
                username = rs.getString("username"),
                password = HashedPassword(rs.getString("password")),
            )
    }
}
