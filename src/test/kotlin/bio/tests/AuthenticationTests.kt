package bio.tests

import bio.auth.AuthenticationRequest
import bio.auth.InvalidCredentialsAuthenticationException
import bio.auth.bearer.BearerToken
import bio.auth.bearer.BearerTokenAuthenticationService
import bio.data.CachingProvider
import bio.mocks.MockCachingProvider
import bio.mocks.MockUserRepository
import bio.prelude.HashingAlgorithm
import bio.prelude.NoopHashAlgorithm
import bio.users.HashedPassword
import bio.users.PlaintextPassword
import bio.users.UserRepository
import bio.users.UserRow
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.util.UUID

class AuthenticationTests {
    private val cachingProvider: CachingProvider<BearerToken> = MockCachingProvider()
    private val userRepository: UserRepository = MockUserRepository()
    private val hashingAlgorithm: HashingAlgorithm = NoopHashAlgorithm()

    @Nested
    inner class BearerServiceTests {
        private val service =
            BearerTokenAuthenticationService(
                cachingProvider,
                userRepository,
                hashingAlgorithm,
            )

        @Test
        fun `should authenticate the user with provided credentials`() {
            // when
            val sharedPassword = "pass"

            val userRow =
                UserRow(
                    id = UUID.randomUUID(),
                    username = "test",
                    // Plaintext & hashed will be the same value, because Noop implementation is used
                    password = HashedPassword(sharedPassword),
                )
            // Create a new user which will be used to test the authentication
            userRepository.create(userRow)

            val authenticationRequest =
                AuthenticationRequest(
                    username = userRow.username,
                    password = PlaintextPassword(sharedPassword),
                )

            assertDoesNotThrow {
                val token = service.authenticate(authenticationRequest)

                val authenticated = service.isAuthenticated(userRow.username, token)
                assertTrue(authenticated)
            }
        }

        @Test
        fun `should throw invalid credentials exception when user provides bad credentials`() {
            // when
            val userRow =
                UserRow(
                    id = UUID.randomUUID(),
                    username = "test",
                    // Plaintext & hashed will be the same value, because Noop implementation is used
                    password = HashedPassword("other_password"),
                )
            // Create a new user which will be used to test the authentication
            userRepository.create(userRow)

            val authenticationRequest =
                AuthenticationRequest(
                    username = userRow.username,
                    password = PlaintextPassword("password"),
                )

            assertThrows<InvalidCredentialsAuthenticationException> {
                service.authenticate(authenticationRequest)
            }
        }
    }
}
