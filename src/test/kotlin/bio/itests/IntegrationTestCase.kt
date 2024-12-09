package bio.itests

import bio.auth.BearerTokenAuthenticationService
import bio.data.CachingProviderConfig
import bio.data.Config
import bio.data.FlywayMigrations
import bio.data.RedisCachingProvider
import bio.data.SQLConfig
import bio.data.SQLConnector
import bio.prelude.NoopHashAlgorithm
import bio.users.UnsafeUserRepository
import bio.users.UserService

open class IntegrationTestCase {
    protected val config = Config.fromApplicationConfig<SQLConfig>(Config.INTEGRATION_TESTS_DB)
    protected val connector = SQLConnector(config)
    protected val cachingProviderConfig =
        Config.fromApplicationConfig<CachingProviderConfig>(Config.INTEGRATION_TESTS_CACHE)
    protected val cachingProvider = RedisCachingProvider(cachingProviderConfig)
    protected val hashingAlgorithm = NoopHashAlgorithm()
    protected val userRepository = UnsafeUserRepository(connector)
    protected val userService = UserService(hashingAlgorithm, userRepository)
    protected val authenticationService =
        BearerTokenAuthenticationService(cachingProvider, userRepository, hashingAlgorithm)

    private var migrated: Boolean = false

    fun ensureMigrated() {
        if (!migrated) {
            FlywayMigrations.migrate(config)
        }

        migrated = true
    }
}
