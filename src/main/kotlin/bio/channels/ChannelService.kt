package bio.channels

import java.util.UUID

class ChannelService(private val channelRepository: ChannelRepository) {
    fun createChannel(channelCreationRequest: ChannelCreationRequest): Channel? {
        val channel = ChannelRow(UUID.randomUUID(), channelCreationRequest.name);
        val created = channelRepository.create(channel);
        return created?.toDomainObject();
    }

    fun getAll(): List<Channel> {
        val rows = channelRepository.getAll();
        return rows.map { x -> x.toDomainObject() }
    }
}
