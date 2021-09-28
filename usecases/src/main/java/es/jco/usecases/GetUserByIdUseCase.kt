package es.jco.usecases

import es.jco.data.common.ResultData
import es.jco.data.repository.UserRepository
import es.jco.domain.User

class GetUserByIdUseCase (private val userRepository: UserRepository) {

    suspend operator fun invoke(userId: Long): ResultData<User> = userRepository.getUser(userId)
}