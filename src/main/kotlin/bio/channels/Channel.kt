package bio.channels

import java.util.UUID

data class Channel(
    val id: UUID,
    val name: String,
)

fun ChannelRow.toDomainObject(): Channel = Channel(this.id, this.name)
