package bio.users

import bio.prelude.Hashable

sealed interface Password

/**
 * `HashedPassword` represents the hashed password.
 * It's used for type safety between plaintext and hashed content in the code.
 */
data class HashedPassword(
    val value: String,
) : Password {
    override fun toString(): String = value
}


/**
 * `PlaintextPassword` represents the plaintext password.
 * It's used for type safety between plaintext and hashed content in the code.
 */
data class PlaintextPassword(
    override val value: String,
) : Password, Hashable {
    override fun toString(): String = value
}
