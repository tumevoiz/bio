package bio.auth

import bio.users.PlaintextPassword

data class AuthenticationRequest(
    val username: String,
    val password: PlaintextPassword,
)
