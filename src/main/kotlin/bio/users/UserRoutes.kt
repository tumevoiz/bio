package bio.users

import org.http4k.contract.ContractRoute
import org.http4k.contract.meta
import org.http4k.core.*
import org.http4k.format.Jackson.auto

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
}
