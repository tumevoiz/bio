package bio.auth

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.annotation.JsonCreator.Mode.DELEGATING as m

data class BearerToken @JsonCreator(mode = m) constructor(
    @JsonValue override val value: String,
) : Token {
    companion object {
        fun generateToken(): BearerToken = BearerToken("test")

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
