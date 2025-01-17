package bio.auth

import java.util.UUID


data class BearerToken(
    override val value: String,
) : Token {
    companion object {
        fun generateToken(): BearerToken = BearerToken(UUID.randomUUID().toString().substring(0,15))

        const val FINGERPRINT: String = "bearer"
    }

    override fun equals(other: Any?): Boolean {
        var equals = false
        other?.let {
            equals =
                when (it) {
                    is BearerToken -> it.value == this.value
                    else -> false
                }
        }

        return equals
    }

    override fun hashCode(): Int {
        var result = value.hashCode()
        result = 31 * result + fingerprint.hashCode()
        return result
    }

    override val fingerprint: String = FINGERPRINT
}
