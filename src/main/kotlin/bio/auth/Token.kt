package bio.auth

/**
 * `Token` is object which will be required every hit to API.
 * It could be a session id, bearer token or JWT (depends on implementation).
 */
interface Token {
    val value: String
    val fingerprint: String
}

