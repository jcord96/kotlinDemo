package es.jco.usecases

import es.jco.data.common.ResultData
import es.jco.data.repository.UserRepository

class DeleteUserByIdUseCase (private val userRepository: UserRepository) {

    suspend operator fun invoke(userId: Long): ResultData<Boolean> = userRepository.deleteUser(userId)
}