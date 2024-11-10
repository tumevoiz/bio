package bio.data

import bio.auth.AuthenticationCachingFeatures
import bio.auth.Token

/**
 * `CachingConnector` is abstraction for caching providers like Redis or Memcached.
 */
abstract class CachingProvider<T : Token> : AuthenticationCachingFeatures<T> {
    protected val tokenName = { username: String -> "${username}_token" }
    protected val tokenValue = { value: String, fingerprint: String -> "${value}:${fingerprint}" }
    protected val parseToken = { tokenWithFingerprint: String ->
        val s = tokenWithFingerprint.split(":")
        Pair(s[0], s[1])
    }
}
