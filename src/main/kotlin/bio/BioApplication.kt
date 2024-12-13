package bio

import bio.auth.AuthenticationFilter
import bio.auth.AuthenticationRoutes
import bio.auth.BearerTokenAuthenticationService
import bio.channels.ChannelRoutes
import bio.channels.ChannelService
import bio.channels.UnsafeChannelRepository
import bio.data.*
import bio.prelude.Argon2HashingAlgorithm
import bio.users.*
import org.http4k.contract.ContractRoute
import bio.messages.MessageRoutes
import bio.messages.MessageService
import bio.messages.UnsafeMessageRepository
import org.http4k.contract.bind
import org.http4k.contract.contract
import org.http4k.contract.meta
import org.http4k.contract.openapi.ApiInfo
import org.http4k.contract.openapi.v3.ApiServer
import org.http4k.contract.openapi.v3.OpenApi3
import org.http4k.contract.security.BearerAuthSecurity
import org.http4k.contract.ui.swaggerUiLite
import org.http4k.core.*
import org.http4k.core.Status.Companion.OK
import org.http4k.filter.CorsPolicy
import org.http4k.filter.ServerFilters
import org.http4k.format.Jackson
import org.http4k.routing.routes
import org.http4k.server.Undertow
import org.http4k.server.asServer

private const val HTTP_SERVER_PORT = 9000

fun main() {
    val cachingProviderConfig = Config.fromApplicationConfig<CachingProviderConfig>(Config.CACHE)
    val sqlConfig = Config.fromApplicationConfig<SQLConfig>(Config.DB)

    val cachingProvider = RedisCachingProvider(cachingProviderConfig)
    val connector = SQLConnector(sqlConfig)
    val hashingAlgorithm = Argon2HashingAlgorithm()

    val userRepository = UnsafeUserRepository(connector)
    val channelRepository = UnsafeChannelRepository(connector)
    val messageRepository = UnsafeMessageRepository(connector)

    val authenticationService = BearerTokenAuthenticationService(cachingProvider, userRepository, hashingAlgorithm)
    val channelService = ChannelService(channelRepository)
    val messageService = MessageService(messageRepository)

    val authenticationRoutes = AuthenticationRoutes(authenticationService)
    val authFilter = AuthenticationFilter(authenticationService)

    val userService = UserService(hashingAlgorithm, userRepository)
    val userRoutes = UserRoutes(userService)

    val channelRoutes = ChannelRoutes(channelService)
    val messageRoutes = MessageRoutes(messageService)

    val contract = contract {
        renderer = OpenApi3(
            ApiInfo("Bio API", "v1.0"),
            Jackson,
            servers = listOf(ApiServer(Uri.of("http://localhost:9000")))
        )
        descriptionPath = "/docs/openapi.json"
        routes += authenticationRoutes.login()
        routes += userRoutes.createUser()
        routes += healthRoute(authFilter)
        routes += channelRoutes.createChannel()
        routes += channelRoutes.getChannels()
        routes += messageRoutes.createMessage()
    }

    val api = routes(
        "/api" bind contract,
        swaggerUiLite {
            url = "/api/docs/openapi.json"
        }
    )

    val server = ServerFilters.Cors(CorsPolicy.UnsafeGlobalPermissive).then(api).asServer(Undertow(HTTP_SERVER_PORT)).start()

    println("Server started on " + server.port())
}

fun healthRoute(authFilter: AuthenticationFilter): ContractRoute {
    return "/health_check" meta {
        summary = "Health Check"
        description = "Health check for application (secured)"
        security = BearerAuthSecurity(authFilter)
    } bindContract Method.GET to { req: Request ->
        Response(OK).body("User UUID is ${req.header("x-user-uuid")}")
    }
}
