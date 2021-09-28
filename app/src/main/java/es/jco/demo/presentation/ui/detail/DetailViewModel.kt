package es.jco.demo.presentation.ui.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.jco.data.common.ResultData
import es.jco.domain.User
import es.jco.usecases.GetUserByIdUseCase
import es.jco.usecases.SaveUserUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Detail view model
 *
 * @property getUserByIdUseCase
 * @property saveUserUseCase
 * @constructor Create empty Detail view model
 */
@HiltViewModel
class DetailViewModel @Inject constructor(private val getUserByIdUseCase: GetUserByIdUseCase, private val saveUserUseCase: SaveUserUseCase) :
    ViewModel() {

    companion object {
        private val TAG = DetailActivity::class.qualifiedName
        private val requestDispatcher: CoroutineDispatcher = Dispatchers.IO
    }

    /** StateFlow to indicate the selected user */
    private val _user = MutableStateFlow(User())
    val user: StateFlow<User> get() = _user

    /** StateFlow to indicate when inputs can be edited */
    private val _isInputEnabled = MutableStateFlow(false)
    val isInputEnabled: StateFlow<Boolean> get() = _isInputEnabled

    /** StateFlow to indicate when io thread is loading data */
    private val _loading = MutableStateFlow(true)
    val loading: StateFlow<Boolean> get() = _loading

    /** Variable to save the modified data */
    var userEditable = User()

    /**
     * Request to get user by id from database
     *
     * @param userId
     */
    suspend fun loadUser(userId: Long) {
        Log.i(TAG, "Loading user - $userId")

        // Request launched in io thread
        withContext(requestDispatcher) {
            when (val resultData = getUserByIdUseCase.invoke(userId)) {
                is ResultData.Success -> onSuccessGetUser(resultData.value)
                is ResultData.Failure -> onErrorGetUser(resultData.throwable)
            }
        }
    }

    /**
     * Function to handle when getUserByID request succeeds
     *
     * @param user Selected user
     */
    private fun onSuccessGetUser(user: User) {
        viewModelScope.launch(requestDispatcher)  {
            _user.emit(user)
            userEditable = user
        }
    }

    /**
     * Function to handle when getUserByID request fails
     *
     * @param throwable Exception
     */
    private fun onErrorGetUser(throwable: Throwable) {
        Log.e(TAG, if (throwable.localizedMessage.isNullOrEmpty()) "Unexpected error" else throwable.localizedMessage)
        viewModelScope.launch(requestDispatcher) {
            _isInputEnabled.emit(false)
        }
    }

    /**
     * Request to save user by id on server and database
     *
     */
    fun saveUser() {
        Log.i(TAG, "Detail ViewModel - Saving user: $userEditable")
        if (checkData()) {
            // Request launched in io thread
            viewModelScope.launch(requestDispatcher) {
                _loading.emit(true)

                when (val resultData = saveUserUseCase.invoke(userEditable)) {
                    is ResultData.Success -> onSuccessSaveUser(resultData.value)
                    is ResultData.Failure -> onErrorSaveUser(resultData.throwable)
                }
            }
        }
    }

    /**
     * Function to handle when saveUser request succeeds
     *
     * @param user Saved user
     */
    private fun onSuccessSaveUser(user: User) {
        viewModelScope.launch(requestDispatcher) {
            _user.emit(user)
            _isInputEnabled.emit(false)
            _loading.emit(false)
            userEditable = user
        }
    }

    /**
     * Function to handle when saveUser request fails
     *
     * @param throwable Exception
     */
    private fun onErrorSaveUser(throwable: Throwable) {
        Log.e(TAG, if (throwable.localizedMessage.isNullOrEmpty()) "Unexpected error" else throwable.localizedMessage)
        viewModelScope.launch(requestDispatcher) {
            _isInputEnabled.emit(false)
            _loading.emit(false)
        }
    }

    /** Function to emit the status of inputs to enabled */
    fun enableInput() = viewModelScope.launch { _isInputEnabled.emit(true) }
    /** Function to emit the status of inputs to disabled */
    fun disableInput() = viewModelScope.launch { _isInputEnabled.emit(false) }

    /** Function to emit the state of loading */
    fun loading() = viewModelScope.launch { _loading.emit(true) }
    /** Function to emit the state of finish loading */
    fun finishLoading() = viewModelScope.launch { _loading.emit(false) }

    /** Function to validate the fields */
    private fun checkData() = !userEditable.name.isNullOrBlank()
}