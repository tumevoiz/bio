package bio.users

data class UserCreationRequest(
    val username: String,
    val password: PlaintextPassword,
)
