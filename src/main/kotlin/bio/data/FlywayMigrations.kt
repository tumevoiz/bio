package bio.data

import org.flywaydb.core.Flyway
import java.util.logging.Logger

object FlywayMigrations {
    private val logger = Logger.getLogger(javaClass.name)

    fun migrate(properties: SQLConfig?) {
        logger.info("Starting migrations.")
        properties?.let {
            logger.info("Loaded properties.")
            val flyway =
                Flyway
                    .configure()
                    .dataSource(
                        it.url,
                        it.username,
                        it.password,
                    ).baselineOnMigrate(true)
                    .load()

            flyway.migrate()
        }
    }
}
