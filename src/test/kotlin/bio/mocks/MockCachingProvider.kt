package bio.mocks

import bio.auth.InvalidTokenException
import bio.auth.bearer.BearerToken
import bio.data.CachingProvider

class MockCachingProvider : CachingProvider<BearerToken>() {
    private val storage: MutableMap<String, String> = mutableMapOf()

    override fun saveToken(token: BearerToken, username: String): Boolean {
        storage[tokenName(username)] = tokenValue(token.value, token.fingerprint)
        val exists = storage[tokenName(username)] != null
        return exists
    }

    override fun getTokenForUser(username: String): BearerToken {
        val token = storage[tokenName(username)]?.let {
            val (value, fingerprint) = parseToken(it)
            BearerToken(value)
        } ?: throw InvalidTokenException()

        return token
    }
}
