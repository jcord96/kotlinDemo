package es.jco.usecases

import es.jco.data.common.ResultData
import es.jco.data.repository.UserRepository

class LoadUsersUseCase (private val userRepository: UserRepository) {

    suspend operator fun invoke(): ResultData<Boolean> = userRepository.loadUsers()
}