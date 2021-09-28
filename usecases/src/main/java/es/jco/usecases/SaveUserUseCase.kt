package es.jco.usecases

import es.jco.data.common.ResultData
import es.jco.data.repository.UserRepository
import es.jco.domain.User

class SaveUserUseCase (private val userRepository: UserRepository) {

    suspend operator fun invoke(user: User): ResultData<User> = userRepository.saveUser(user)
}