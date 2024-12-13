package bio.auth

import org.http4k.core.Filter
import org.http4k.core.HttpHandler
import org.http4k.core.Response
import org.http4k.core.Status

class AuthenticationFilter(private val authenticationService: AuthenticationService<BearerToken>): Filter {
    override fun invoke(next: HttpHandler): HttpHandler = { req ->
        req.header("Authorization")?.let { token ->
            println("Got header: $token")
            val tokenValue = token.split(" ")[1]
            val userUUID = authenticationService.getUserUUIDByToken(BearerToken(tokenValue))
                ?: throw RuntimeException("Failed to get by token")
            println("Got uuid: $userUUID")
            next(req.header("x-user-uuid", userUUID.toString()))
        } ?: Response(Status.UNAUTHORIZED).body("No token given.")
    }
}
