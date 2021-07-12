package com.cvitirinyu.codingchallenge.starwars.data.network

import android.util.Log
import androidx.annotation.VisibleForTesting
import retrofit2.Response
import java.io.IOException

class NetworkCallHandlerImpl: NetworkCallHandler {

    override suspend fun <T> makeNetworkCall(
        call: suspend () -> Response<T>
    ): ServiceResult<T> {
        return try {
            val networkCall = call()
            val body = networkCall.body()
            if (networkCall.isSuccessful && body != null) {
                ServiceResult.Success(body)
            } else {
                networkCall.handleCallFailed()
            }
        } catch (exception: Exception) {
            handleException(exception)
        }
    }

    private fun handleException(exception: Exception): ServiceResult.NetworkException {
        return when (exception) {
            is IOException -> {
                logExceptionMessage(
                    "No Internet detected",
                    "NETWORK IS NOT CONNECTED!"
                )
                ServiceResult.NetworkException(exception)
            }
            else -> {
                logExceptionMessage(
                    "Error-NetworkCallHandler",
                    exception.localizedMessage ?: ""
                )
                ServiceResult.NetworkException(exception)
            }
        }
    }

    private fun <T> Response<T>.handleCallFailed(): ServiceResult.Error {
        logExceptionMessage(
            code().toString(),
            message().orEmpty()
        )
        Log.e(TAG, "${code().toString()}: ${errorBody().toString()}")

        return ServiceResult.Error
    }

    @VisibleForTesting
    internal fun logExceptionMessage(code: String?, message: String) {
        val codedMessage = if (code == null) message else "with code: $code, error message: message"
        Log.e(TAG, codedMessage)
    }

    companion object {
        private const val TAG = "NetworkCallHandler"
    }

}