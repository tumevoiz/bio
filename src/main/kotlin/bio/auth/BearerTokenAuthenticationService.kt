package bio.auth

import bio.data.CachingProvider
import bio.prelude.HashingAlgorithm
import bio.users.UserRepository
import java.util.UUID

class BearerTokenAuthenticationService(
    private val cachingProvider: CachingProvider<BearerToken>,
    private val userRepository: UserRepository,
    private val hashingAlgorithm: HashingAlgorithm,
) : AuthenticationService<BearerToken> {
//    private val userRepository: UserRepository by di.instance()
//    private val hashingAlgorithm: HashingAlgorithm by di.instance()

    override fun authenticate(authenticationRequest: AuthenticationRequest): BearerToken {
        val user =
            userRepository.findByUsername(authenticationRequest.username)
                ?: throw InvalidCredentialsAuthenticationException()

        if (!hashingAlgorithm.check(authenticationRequest.password.value, user.password.value)) {
            throw InvalidCredentialsAuthenticationException()
        }

        val token = BearerToken.generateToken()
        if (!cachingProvider.saveToken(token, user.username)) {
            throw TokenGenerationException()
        }

        return token
    }

    override fun getUserUUIDByToken(token: BearerToken): UUID? {
        val username = cachingProvider.getUsernameByToken(token)
        println("Got username $username")
        username?.let {
            val user = userRepository.findByUsername(it)
            return user?.id
        }

        return null
    }

    override fun isAuthenticated(
        username: String,
        token: BearerToken,
    ): Boolean {
        val foundToken = cachingProvider.getTokenForUser(username)
        return foundToken == token
    }

    fun refreshToken(username: String): BearerToken {
        val user = userRepository.findByUsername(username)
            ?: throw InvalidCredentialsAuthenticationException()

        val newToken = BearerToken.generateToken()

        if (!cachingProvider.saveToken(newToken, username)) {
            throw TokenGenerationException()
        }

        return newToken
    }

}
