package bio.data

import java.io.IOException
import java.io.InputStream
import java.util.Properties
import kotlin.reflect.full.primaryConstructor

abstract class Config {
    /**
     * `properties` holds all loaded properties.
     */
    abstract val properties: Properties

    companion object {
        /**
         * `fromApplicationConfig` loads properties automatically from .properties files in `resources` folder.
         */
        inline fun <reified C : Config> fromApplicationConfig(propertiesFileName: String): C? {
            val inputStream: InputStream = C::class.java
                .classLoader
                .getResourceAsStream("${propertiesFileName}.properties")
                ?: throw IOException("Cannot find properties file!")

            val properties = Properties()
            properties.load(inputStream)

            val kClassConfigRef = C::class
            val kClassPrimaryConstructor = kClassConfigRef.primaryConstructor
            val instance = kClassPrimaryConstructor?.call(properties)

            return instance
        }

        const val CACHE = "cache"
        const val DB = "db"
        const val INTEGRATION_TESTS_CACHE = "cache"
        const val INTEGRATION_TESTS_DB = "db-test"
    }
}

