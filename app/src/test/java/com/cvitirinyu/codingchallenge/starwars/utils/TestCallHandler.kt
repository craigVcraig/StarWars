package com.cvitirinyu.codingchallenge.starwars.utils

import com.cvitirinyu.codingchallenge.starwars.data.network.NetworkCallHandler
import com.cvitirinyu.codingchallenge.starwars.data.network.ServiceResult
import retrofit2.Response

@Suppress("UNCHECKED_CAST")
class TestCallHandler: NetworkCallHandler {
    var callResult: ServiceResult<*>? = null

    override suspend fun <T> makeNetworkCall(call: suspend () -> Response<T>): ServiceResult<T> {
       return callResult as ServiceResult<T>
    }
}