package bio.auth

open class AuthenticationException(
    message: String?,
    cause: Throwable?,
) : RuntimeException("An authentication attempt has failed: $message", cause)

class InvalidCredentialsAuthenticationException : AuthenticationException("Invalid credentials", null)

class TokenGenerationException : AuthenticationException("Cannot generate token", null)
