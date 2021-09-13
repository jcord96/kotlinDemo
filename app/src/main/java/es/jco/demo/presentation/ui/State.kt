package es.jco.demo.presentation.ui

sealed class State<out T> {
    object Loading : State<Nothing>()

    data class Success<T>(var data: T) : State<T>()
    data class Failure(var throwable: Throwable) : State<Nothing>()
}