package es.jco.demo.presentation.ui

sealed class State<out T> {
    object OnLoading : State<Nothing>()

    data class OnSuccess<T>(var data: T) : State<T>()
    data class OnFailure(var throwable: Throwable) : State<Nothing>()
}