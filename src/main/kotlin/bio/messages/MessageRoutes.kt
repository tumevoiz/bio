package bio.messages

import bio.auth.AuthenticationFilter
import bio.channels.Channel
import org.http4k.contract.ContractRoute
import org.http4k.contract.div
import org.http4k.contract.meta
import org.http4k.contract.security.BearerAuthSecurity
import org.http4k.core.*
import org.http4k.format.Jackson.auto
import org.http4k.format.Jackson.json
import org.http4k.lens.Path
import org.http4k.lens.Query
import org.http4k.lens.string
import org.http4k.routing.path
import java.awt.Container
import java.time.OffsetDateTime
import java.util.UUID

class MessageRoutes(private val messageService: MessageService) {
    fun createMessage(authFilter: AuthenticationFilter): ContractRoute {
        val messageCreationRequestLens = Body.auto<MessageCreationRequest>().toLens()
        return "/messages" meta {
            summary = "Create message"
            description = "Create a new message"
            receiving(messageCreationRequestLens to MessageCreationRequest(UUID.randomUUID(),"Hello"))
            returning(Status.OK to "Message creation status.")
            security = BearerAuthSecurity(authFilter)
        } bindContract Method.POST to { req: Request ->
            messageCreationRequestLens(req).let { messageCreationRequest ->
                val userUUID = UUID.fromString(req.header("x-user-uuid"))
                messageService.createMessage(messageCreationRequest, userUUID)
                Response(Status.OK).body("Message created successfully.")
            }
        }
    }

    fun getChannelMessages(authFilter: AuthenticationFilter): ContractRoute {
        val responseLens = Body.auto<List<Message>>().toLens()
        val channelUUIDQuery = Query.string()
            .required("channelUUID", "channelUUID")

        return "/messages" meta {
            summary = "Get channel messages"
            description = "Get channel messages"
            returning(Status.OK, responseLens to listOf(Message(
                UUID.randomUUID(),
                UUID.randomUUID(),
                "test",
                OffsetDateTime.now()
            )))
            security = BearerAuthSecurity(authFilter)
            queries += channelUUIDQuery
        } bindContract Method.GET to { req: Request ->
            val channelUUID = UUID.fromString(channelUUIDQuery(req))
            val messages = messageService.getChannelMessages(channelUUID)

            Response(Status.OK).json(messages)
        }
    }
}
