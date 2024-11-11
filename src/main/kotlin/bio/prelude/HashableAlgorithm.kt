package bio.prelude

import de.mkammerer.argon2.Argon2Factory

interface HashingAlgorithm {
    fun hash(input: String): HashedString

    fun check(
        plain: String,
        hash: HashedString,
    ): Boolean
}

class NoopHashAlgorithm : HashingAlgorithm {
    override fun hash(input: String): HashedString = input

    override fun check(
        plain: String,
        hash: HashedString,
    ): Boolean = plain == hash
}

/**
 * `OverrideHashingAlgorithm` is used for testing the inline method
 * and HashingAlgorithm `hash` function overriding.
 *
 * > NOTE: USE ONLY FOR TESTS PURPOSES.
 */
class OverrideHashingAlgorithm : HashingAlgorithm {
    override fun hash(input: String): HashedString = "override"

    override fun check(
        plain: String,
        hash: HashedString,
    ): Boolean = hash == "override"
}

class Argon2HashingAlgorithm: HashingAlgorithm {
    private val argon2 = Argon2Factory.create()

    override fun hash(input: String): HashedString {
        return argon2.hash(10, 65536, 1, input.toCharArray())
    }

    override fun check(plain: String, hash: HashedString): Boolean {
        return argon2.verify(hash, plain.toCharArray())
    }

}
