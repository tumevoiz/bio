package bio.messages

open class MessageException(
    message: String,
    cause: Throwable? = null,
) : RuntimeException(message, cause)

class MessageNotCreatedException(
    cause: Throwable? = null,
) : MessageException("Cannot create message!", cause)

class MessageNotFoundException(
    cause: Throwable? = null,
) : MessageException("Cannot find message!", cause)

class MessageNotReceivedException(
    cause: Throwable? = null,
) : MessageException("Cannot receive message!", cause)
