package bio.auth

interface AuthenticationService<T : Token> {
    fun authenticate(authenticationRequest: AuthenticationRequest): T

    fun isAuthenticated(
        username: String,
        token: T,
    ): Boolean
}
