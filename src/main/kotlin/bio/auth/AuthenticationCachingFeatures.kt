package bio.auth

interface AuthenticationCachingFeatures<T : Token> {
    fun saveToken(
        token: T,
        username: String,
    ): Boolean

    fun getTokenForUser(username: String): T

    fun getUsernameByToken(token: T): String?
}
