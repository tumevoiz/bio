package bio.auth

import java.util.UUID

interface AuthenticationService<T : Token> {
    fun authenticate(authenticationRequest: AuthenticationRequest): T

    fun isAuthenticated(
        username: String,
        token: T,
    ): Boolean

    fun getUserUUIDByToken(token: T): UUID?
}
