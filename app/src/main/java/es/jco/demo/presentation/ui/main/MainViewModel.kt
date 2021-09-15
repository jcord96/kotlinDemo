package es.jco.demo.presentation.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.jco.data.common.ResultData
import es.jco.domain.User
import es.jco.usecases.GetUsersUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getUsersUseCase: GetUsersUseCase) :
    ViewModel() {

    companion object {
        private val TAG = MainViewModel::class.qualifiedName
    }

    private val _loading = MutableStateFlow(true)
    val loading: StateFlow<Boolean> get() = _loading

    private val _users = MutableStateFlow(emptyList<User>())
    val users: StateFlow<List<User>> get() = _users

    suspend fun getUsers() {
        Log.i(TAG, "Getting users - Loading")
        _loading.emit(true)

        when (val resultData = getUsersUseCase.invoke()) {
            is ResultData.Failure -> onErrorGetUsers(resultData.throwable)
            is ResultData.Success -> onSuccessGetUsers(resultData.value)
        }
    }

    private suspend fun onErrorGetUsers(throwable: Throwable) {
        Log.e(TAG, "Getting users - Error thrown")
        Log.e(TAG, "Cause error: ${throwable.localizedMessage}")
        _loading.emit(false)

    }

    private suspend fun onSuccessGetUsers(value: Flow<List<User>>) {
        Log.i(TAG, "Getting users - Successfully ")

        viewModelScope.launch {
            _loading.emit(false)
            value.onEach { _users.emit(it) }.launchIn(this)
        }
    }
}