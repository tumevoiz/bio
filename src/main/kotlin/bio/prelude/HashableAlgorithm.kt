package bio.prelude

interface HashingAlgorithm {
    fun hash(input: String): HashedString
    fun check(plain: String, hash: HashedString): Boolean
}

class NoopHashAlgorithm : HashingAlgorithm {
    override fun hash(input: String): HashedString {
        return input
    }

    override fun check(plain: String, hash: HashedString): Boolean =
        plain == hash
}

/**
 * `OverrideHashingAlgorithm` is used for testing the inline method
 * and HashingAlgorithm `hash` function overriding.
 *
 * > NOTE: USE ONLY FOR TESTS PURPOSES.
 */
class OverrideHashingAlgorithm : HashingAlgorithm {
    override fun hash(input: String): HashedString {
        return "override"
    }

    override fun check(plain: String, hash: HashedString): Boolean =
        hash == "override"
}

// TODO: Add argon2 hashing algorithm
