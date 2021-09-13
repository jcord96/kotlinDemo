package es.jco.demo.presentation.ui.splashscreen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import es.jco.data.common.ResultData
import es.jco.demo.presentation.ui.State
import es.jco.usecases.LoadUsersUseCase
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val loadUsersUseCase: LoadUsersUseCase
) : ViewModel() {

    // During screen startup, data is preloaded to streamline requests
    suspend fun loadUsers() = flow {
        emit(State.Loading)

        when (val resultData = loadUsersUseCase.invoke()) {
            is ResultData.Success -> emit(State.Success(resultData.value))
            is ResultData.Failure -> emit(State.Failure(resultData.throwable))
        }
    }
}