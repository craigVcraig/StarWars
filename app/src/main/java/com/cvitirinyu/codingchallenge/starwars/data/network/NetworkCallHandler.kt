package com.cvitirinyu.codingchallenge.starwars.data.network

import retrofit2.Response
import com.cvitirinyu.codingchallenge.starwars.data.network.ServiceResult

interface NetworkCallHandler {
    suspend fun <T> makeNetworkCall(call: suspend () -> Response<T>): ServiceResult<T>
}