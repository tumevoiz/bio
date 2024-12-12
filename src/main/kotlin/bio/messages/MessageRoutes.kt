package bio.messages

import org.http4k.contract.ContractRoute
import org.http4k.contract.meta
import org.http4k.core.*
import org.http4k.format.Jackson.auto
import java.util.UUID

class MessageRoutes(private val messageService: MessageService) {
    fun createMessage(): ContractRoute {
        val messageCreationRequestLens = Body.auto<MessageCreationRequest>().toLens()
        return "/messages" meta {
            summary = "Create message"
            description = "Create a new message"
            receiving(messageCreationRequestLens to MessageCreationRequest(UUID.randomUUID(), "Hello"))
            returning(Status.OK to "Message creation status.")
        } bindContract Method.POST to { req: Request ->
            messageCreationRequestLens(req).let { messageCreationRequest ->
                val message = messageService.createMessage(messageCreationRequest)
                Response(Status.OK).body("Message created successfully.")
            }
        }
    }
}
