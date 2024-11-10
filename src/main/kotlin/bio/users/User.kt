package bio.users

data class User(
    val username: String,
    val password: HashedPassword,
)

fun UserRow.toDomainObject(): User =
    User(
        username = this.username,
        password = this.password,
    )
