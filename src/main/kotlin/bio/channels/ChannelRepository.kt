package bio.channels

import bio.prelude.Repository

interface ChannelRepository : Repository<Int, ChannelRow> {
    fun getAll(): Collection<ChannelRow>;
}
