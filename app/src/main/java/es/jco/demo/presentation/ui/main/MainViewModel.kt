package es.jco.demo.presentation.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.jco.data.common.ResultData
import es.jco.domain.User
import es.jco.usecases.DeleteUserByIdUseCase
import es.jco.usecases.GetUsersUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Main view model
 *
 * @property getUsersUseCase
 * @property deleteUserByIdUseCase
 * @constructor Create empty Main view model
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val deleteUserByIdUseCase: DeleteUserByIdUseCase
) :
    ViewModel() {

    companion object {
        private val TAG = MainViewModel::class.qualifiedName
        private val requestDispatcher: CoroutineDispatcher = Dispatchers.IO
    }

    /** StateFlow to indicate when io thread is loading data */
    private val _loading = MutableStateFlow(true)
    val loading: StateFlow<Boolean> get() = _loading

    /** StateFlow to indicate all users in live from database */
    private val _users = MutableStateFlow(emptyList<User>())
    val users: StateFlow<List<User>> get() = _users

    /**
     * Request to get users from database
     */
    fun getUsers() {
        Log.i(TAG, "Getting users - Loading")
        // Request launched in io thread
        viewModelScope.launch(requestDispatcher) {

            // The progress bar is activated to indicate that there is a process loading
            loading()

            when (val resultData = getUsersUseCase.invoke()) {
                is ResultData.Success -> onSuccessGetUsers(resultData.value)
                is ResultData.Failure -> onErrorGetUsers(resultData.throwable)
            }

            // The progress bar turns off when the request response is returned
            finishLoading()
        }
    }

    /**
     * Function to handle when getUsers request succeeds
     *
     * @param value Flowable with returned users list
     */
    private suspend fun onSuccessGetUsers(value: Flow<List<User>>) {
        Log.i(TAG, "Getting users - Successfully ")

        // A flow is returned to get all users every time that there are changes to the user entity
        // When a user is created, updated or deleted, this flow will be notified and the recycler view list will update

        // Request launched in io thread
        viewModelScope.launch {
            value.onEach { _users.emit(it) }.launchIn(this)
        }
    }

    /**
     * Function to handle when getUsers request fails
     *
     * @param throwable Exception
     */
    private fun onErrorGetUsers(throwable: Throwable) {
        Log.e(TAG, "Getting users - Error thrown")
        Log.e(TAG, "Cause error: ${throwable.localizedMessage}")
    }

    /**
     * Request to delete user on server and database
     *
     * @param userId Id user to delete
     */
    fun deleteUser(userId: Long) {
        viewModelScope.launch(requestDispatcher) {
            // The progress bar is activated to indicate that there is a process loading
            loading()

            when (val resultData = deleteUserByIdUseCase.invoke(userId)) {
                is ResultData.Failure -> onErrorDeleteUser(resultData.throwable)
                is ResultData.Success -> onSuccessDeleteUser(resultData.value)
            }

            // The progress bar turns off when the request response is returned
            finishLoading()
        }
    }

    /**
     * Function to handle when deleteUser request succeeds
     *
     * @param value Boolean with result
     */
    private fun onSuccessDeleteUser(value: Boolean) {
        Log.i(TAG, "Deleting users - Successfully ")
    }

    /**
     * Function to handle when deleteUser request fails
     *
     * @param throwable Exception
     */
    private fun onErrorDeleteUser(throwable: Throwable) {
        Log.e(TAG, "Deleting user - Error thrown")
        Log.e(TAG, "Cause error: ${throwable.localizedMessage}")
    }

    /** Function to emit the state of loading */
    private fun loading() = viewModelScope.launch { _loading.emit(true) }
    /** Function to emit the state of finish loading */
    private fun finishLoading() = viewModelScope.launch { _loading.emit(false) }
}