package bio.auth

import bio.users.PlaintextPassword
import org.http4k.contract.ContractRoute
import org.http4k.contract.meta
import org.http4k.core.*
import org.http4k.core.Status.Companion.OK
import org.http4k.format.Jackson.auto
import org.http4k.format.Jackson.json

class AuthenticationRoutes(private val authenticationService: AuthenticationService<*>) {
    fun login(): ContractRoute {
        val authenticationRequestLens = Body.auto<AuthenticationRequest>().toLens()
        return "/login" meta {
            summary = "Login"
            description = "Authenticate a user"
            receiving(authenticationRequestLens to AuthenticationRequest("dobry@jezu", PlaintextPassword("anaszpanie")))
            returning(OK to "Authentication token")
        } bindContract Method.POST to { req: Request ->
            authenticationRequestLens(req).let { authenticationRequest ->
                val token = authenticationService.authenticate(authenticationRequest)
                Response(OK).json(AuthenticationResponse(token.value))
            }
        }
    }
}
