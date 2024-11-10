package bio.auth

open class TokenException(message: String, cause: Throwable? = null) : RuntimeException(message, cause)

class InvalidTokenException : TokenException("Invalid token.")
class MalformedTokenException : TokenException("Malformed token.")
