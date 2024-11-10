package bio.mocks

import bio.users.UserRepository
import bio.users.UserRow
import java.util.UUID

class MockUserRepository : UserRepository {
    private val rows: MutableList<UserRow> = mutableListOf()

    override fun findByUsername(username: String): UserRow? {
        return rows.find { row -> row.username == username }
    }

    override fun create(row: UserRow): UserRow {
        rows.add(row)
        return row
    }

    override fun delete(id: UUID): Boolean {
        val predicate = { row: UserRow -> row.id == id }
        rows.removeIf(predicate)
        return rows.find(predicate) == null
    }
}
