package bio.channels

import org.http4k.contract.ContractRoute
import org.http4k.contract.meta
import org.http4k.core.*
import org.http4k.format.Jackson.auto
import org.http4k.format.Jackson.json

class ChannelRoutes(private val channelService: ChannelService) {
    fun createChannel(): ContractRoute {
        val request = Body.auto<ChannelCreationRequest>().toLens()
        return "/channels" meta {
            summary = "Create user"
            description = "Create an user"
            receiving(request to ChannelCreationRequest("nazwaKanalu"))
            returning(Status.CREATED)
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

    fun getChannels(): ContractRoute {
        return "/channels" meta {
            summary = "Get channels list"
            description = "Get channels list"
            returning(Status.OK)
        } bindContract Method.GET to { req: Request ->
            val channels = channelService.getAll();
            Response(Status.OK).json(channels);
        }
    }
}

