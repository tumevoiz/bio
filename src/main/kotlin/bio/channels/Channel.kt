package bio.channels

data class Channel(
    val id: Int,
    val name: String,
)

fun ChannelRow.toDomainObject(): Channel = Channel(this.id, this.name)
