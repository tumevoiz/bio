package bio.data

import java.util.Properties

class CachingProviderConfig(override val properties: Properties) : Config() {
    val host: String = properties.getProperty(CONFIG_CACHE_HOST_KEY)
    val port: String = properties.getProperty(CONFIG_CACHE_PORT_KEY)
    val username: String = properties.getProperty(CONFIG_CACHE_USERNAME_KEY)
    val password: String = properties.getProperty(CONFIG_CACHE_PASSWORD_KEY)

    companion object {
        const val CONFIG_CACHE_HOST_KEY = "cache.host"
        const val CONFIG_CACHE_PORT_KEY = "cache.port"
        const val CONFIG_CACHE_USERNAME_KEY = "cache.username"
        const val CONFIG_CACHE_PASSWORD_KEY = "cache.password"
    }
}
