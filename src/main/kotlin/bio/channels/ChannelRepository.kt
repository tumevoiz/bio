package bio.channels

import bio.prelude.Repository
import java.util.UUID

interface ChannelRepository : Repository<UUID, ChannelRow> {
    fun getAll(): List<ChannelRow>;
}
