package bio.data

import java.util.Properties

class SQLConfig(
    override val properties: Properties,
) : Config() {
    val url: String = properties.getProperty(CONFIG_JDBC_URL_KEY)
    val username: String = properties.getProperty(CONFIG_JDBC_USER_KEY)
    val password: String = properties.getProperty(CONFIG_JDBC_USER_PASSWORD)

    companion object {
        const val CONFIG_JDBC_URL_KEY = "db.url"
        const val CONFIG_JDBC_USER_KEY = "db.username"
        const val CONFIG_JDBC_USER_PASSWORD = "db.password"
    }
}
