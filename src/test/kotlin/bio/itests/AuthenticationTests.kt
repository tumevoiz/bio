package bio.itests

import bio.auth.AuthenticationRequest
import bio.auth.InvalidCredentialsAuthenticationException
import bio.users.PlaintextPassword
import bio.users.UserCreationRequest
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class AuthenticationTests : IntegrationTestCase() {
    @Test
    fun `should authenticate created user`() {
        ensureMigrated()

        // when
        val request =
            UserCreationRequest(
                username = "test",
                password = PlaintextPassword("test"),
            )

        val authRequest =
            AuthenticationRequest(
                username = request.username,
                password = request.password,
            )

        assertDoesNotThrow {
            userService.createUser(request)
            val token = authenticationService.authenticate(authRequest)
            val isAuthenticated = authenticationService.isAuthenticated(request.username, token)
            assertTrue(isAuthenticated)
        }
    }

    @Test
    fun `should not authenticate user with bad password`() {
        ensureMigrated()
        val request =
            UserCreationRequest(
                username = "test",
                password = PlaintextPassword("test"),
            )

        val authRequest =
            AuthenticationRequest(
                username = request.username,
                password = PlaintextPassword("other_password"),
            )

        assertThrows<InvalidCredentialsAuthenticationException> {
            authenticationService.authenticate(authRequest)
        }
    }
}
