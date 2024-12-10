package bio.auth

import bio.users.PlaintextPassword
/*import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.annotation.JsonCreator.Mode.DELEGATING as m*/

data class AuthenticationRequest /*@JsonCreator(mode = m) constructor*/(
    /*@JsonValue*/ val username: String,
    /*@JsonValue*/ val password: PlaintextPassword,
)
