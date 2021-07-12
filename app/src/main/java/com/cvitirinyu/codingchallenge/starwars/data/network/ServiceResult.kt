package com.cvitirinyu.codingchallenge.starwars.data.network

sealed class ServiceResult<out R> {
    data class Success<out T>(val data: T) : ServiceResult<T>()
    object Error : ServiceResult<Nothing>()
    data class NetworkException(val exception: Exception) : ServiceResult<Nothing>()
}

fun <T> ServiceResult<T>.handleSuccessTask(task: (T) -> Unit): ServiceResult<T> {
    if (this is ServiceResult.Success) task.invoke(this.data)
    return this
}