package com.cvitirinyu.codingchallenge.starwars.data.network

sealed class ServiceResult<out R> {
    data class Success<out T>(val data: T) : ServiceResult<T>()
    object Error : ServiceResult<Nothing>()
    data class NetworkException(val exception: Exception) : ServiceResult<Nothing>()
}