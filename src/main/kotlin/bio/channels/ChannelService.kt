package bio.channels

class ChannelService(private val channelRepository: ChannelRepository) {
    fun createChannel(channelCreationRequest: ChannelCreationRequest): Channel? {
        val channel = ChannelRow(0, channelCreationRequest.name);
        val created = channelRepository.create(channel);
        return created?.toDomainObject();
    }

    fun getAll(): Collection<Channel> {
        val rows = channelRepository.getAll();
        return rows.map { x -> x.toDomainObject() }
    }
}
