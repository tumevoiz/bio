package bio.auth.bearer

import bio.auth.AuthenticationRequest
import bio.auth.AuthenticationService
import bio.auth.InvalidCredentialsAuthenticationException
import bio.auth.TokenGenerationException
import bio.data.CachingProvider
import bio.prelude.HashingAlgorithm
import bio.users.UserRepository

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

    override fun isAuthenticated(
        username: String,
        token: BearerToken,
    ): Boolean {
        val foundToken = cachingProvider.getTokenForUser(username)
        return foundToken == token
    }
}
