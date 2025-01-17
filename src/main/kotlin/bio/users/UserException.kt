package bio.users

open class UserException(
    message: String,
    cause: Throwable? = null,
) : RuntimeException(message, cause)

class UserNotCreatedException(
    cause: Throwable? = null,
) : UserException("Cannot create user!", cause)

class UserNotFoundException(
    cause: Throwable? = null,
) : UserException("Cannot find user!", cause)
