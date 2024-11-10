package bio.users

import bio.prelude.HashingAlgorithm
import java.util.UUID

class UserService(private val hashingAlgorithm: HashingAlgorithm, private val userRepository: UserRepository) {
    fun createUser(request: UserCreationRequest): User {
        // TODO: add validations
        val userRow = UserRow(
            id = UUID.randomUUID(),
            username = request.username,
            password = HashedPassword(hashingAlgorithm.hash(request.password.value)),
        )

        val createdUserRow = userRepository.create(userRow)
        return createdUserRow?.toDomainObject()
            ?: throw UserNotCreatedException()
    }
}
