package bio.users

import bio.auth.AuthenticationFilter
import bio.messages.Message
import org.http4k.contract.ContractRoute
import org.http4k.contract.meta
import org.http4k.contract.security.BearerAuthSecurity
import org.http4k.core.*
import org.http4k.format.Jackson.auto
import org.http4k.format.Jackson.json
import org.http4k.lens.Query
import org.http4k.lens.string
import org.http4k.routing.query
import java.util.UUID

class UserRoutes(private val userService: UserService) {
    fun createUser(): ContractRoute {
        val userCreationRequestLens = Body.auto<UserCreationRequest>().toLens()
        return "/users" meta {
            summary = "Create user"
            description = "Create an user"
            receiving(userCreationRequestLens to UserCreationRequest("dobry@jezu", PlaintextPassword("anaszpanie")))
            returning(Status.OK to "User creation status.")
        } bindContract Method.POST to { req: Request ->
            userCreationRequestLens(req).let { userCreationRequest ->
                val user = userService.createUser(userCreationRequest)
                Response(Status.OK).body("User created successfully.")
            }
        }
    }

    fun getUser(authFilter: AuthenticationFilter): ContractRoute {
        val responseLens = Body.auto<UserResponse>().toLens()

        return "/users" meta {
            summary = "Get user"
            description = "Get an user"
            returning(Status.OK, responseLens to UserResponse("dobry@jezu"))
            security = BearerAuthSecurity(authFilter)
            query("userUUID", "userUUID")
        } bindContract Method.GET to { req: Request ->
            val userUUID = UUID.fromString(req.query("userUUID"))
            val user = userService.getUser(userUUID)
            Response(Status.OK).json(user)
        }
    }
}
