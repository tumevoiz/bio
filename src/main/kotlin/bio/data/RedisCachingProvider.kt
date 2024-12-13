package bio.data

import bio.auth.InvalidTokenException
import bio.auth.MalformedTokenException
import bio.auth.BearerToken
import redis.clients.jedis.DefaultJedisClientConfig
import redis.clients.jedis.HostAndPort
import redis.clients.jedis.JedisPooled

class RedisCachingProvider(
    private val config: CachingProviderConfig?,
) : CachingProvider<BearerToken>() {
    private var jedis: JedisPooled? = null

    init {
        config?.let {
            val hostAndPort = HostAndPort(config.host, config.port.toInt())
            val jedisConfig =
                DefaultJedisClientConfig
                    .builder()
                    .user(config.username)
                    .password(config.password)
                    .build()

            jedis = JedisPooled(hostAndPort, jedisConfig)
        }
    }

    override fun saveToken(
        token: BearerToken,
        username: String,
    ): Boolean {
        jedis?.set(tokenName(username), tokenValue(token.value, token.fingerprint))
        return jedis?.exists(tokenName(username)) ?: false
    }

    override fun getTokenForUser(username: String): BearerToken {
        val (token, fingerprint) =
            parseToken(
                jedis?.get(tokenName(username)) ?: throw InvalidTokenException(),
            )
        return when (fingerprint) {
            BearerToken.FINGERPRINT -> BearerToken(token)
            else -> throw MalformedTokenException()
        }
    }

    override fun getUsernameByToken(token: BearerToken): String? {
        val key = jedis?.keys("*token")
            ?.filter { token.value == jedis?.get(it)?.let { it1 -> parseToken(it1).first } }
        return key?.single()?.replace("_token", "")
    }
}
