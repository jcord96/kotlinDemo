package es.jco.usecases

import es.jco.data.common.ResultData
import es.jco.data.repository.UserRepository
import es.jco.domain.User
import kotlinx.coroutines.flow.Flow

class GetUsersUseCase(private val userRepository: UserRepository) {

    suspend operator fun invoke(): ResultData<Flow<List<User>>> = userRepository.getUsers()
}