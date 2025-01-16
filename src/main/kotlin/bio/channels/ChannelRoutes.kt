package bio.channels

import bio.auth.AuthenticationFilter
import org.http4k.contract.ContractRoute
import org.http4k.contract.meta
import org.http4k.contract.security.BearerAuthSecurity
import org.http4k.core.*
import org.http4k.format.Jackson.auto
import org.http4k.format.Jackson.json
import java.util.UUID

class ChannelRoutes(private val channelService: ChannelService) {
    fun createChannel(authFilter: AuthenticationFilter): ContractRoute {
        val request = Body.auto<ChannelCreationRequest>().toLens()
        return "/channels" meta {
            summary = "Create user"
            description = "Create an user"
            receiving(request to ChannelCreationRequest("nazwaKanalu"))
            returning(Status.CREATED)
            security = BearerAuthSecurity(authFilter)
        } bindContract Method.POST to { req: Request ->
            request(req).let { channelCreationRequest ->
                val channel = channelService.createChannel(channelCreationRequest)
                if (channel != null) {
                    Response(Status.CREATED)
                } else {
                    Response(Status.BAD_REQUEST)
                }
            }
        }
    }

    fun getChannels(authFilter: AuthenticationFilter): ContractRoute {
        val responseLens = Body.auto<Collection<Channel>>().toLens()

        return "/channels" meta {
            summary = "Get channels list"
            description = "Get channels list"
            returning(Status.OK, responseLens to listOf(Channel(UUID.randomUUID(),"Nazwa kanalu")))
            security = BearerAuthSecurity(authFilter)
        } bindContract Method.GET to { req: Request ->
            val channels = channelService.getAll();

            Response(Status.OK).json(channels);
        }
    }
}

