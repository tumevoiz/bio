package bio.users

import bio.prelude.Repository
import java.util.UUID

interface UserRepository : Repository<UUID, UserRow> {
    fun findByUsername(username: String): UserRow?
}
