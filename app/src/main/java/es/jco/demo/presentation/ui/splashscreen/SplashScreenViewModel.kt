package es.jco.demo.presentation.ui.splashscreen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import es.jco.data.common.ResultData
import es.jco.demo.presentation.ui.State
import es.jco.usecases.LoadUsersUseCase
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Splash screen view model
 *
 * @property loadUsersUseCase
 * @constructor Create empty Splash screen view model
 */
@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val loadUsersUseCase: LoadUsersUseCase
) : ViewModel() {

    /**
     * Function to load the users from server and save them in db
     *
     * @return flow with result state
     */
    suspend fun loadUsers() = flow {
        // During screen startup, data is preloaded to streamline requests
        emit(State.OnLoading)

        when (val resultData = loadUsersUseCase.invoke()) {
            is ResultData.Success -> emit(State.OnSuccess(resultData.value))
            is ResultData.Failure -> emit(State.OnFailure(resultData.throwable))
        }
    }
}