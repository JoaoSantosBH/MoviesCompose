package br.com.compose.compose_movies_udemy.util

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Response
import java.lang.reflect.Type


typealias OnSuccessNoContent = suspend () -> Unit
typealias OnSuccess<T> = suspend (response: T) -> Unit
typealias OnError = suspend (message: String) -> Unit
typealias OnFinish = suspend () -> Unit

sealed class ResponseWrapper<out T> (
    val hasError: Boolean = false,
    val isSuccess: Boolean = false,
    val isSessionExpired: Boolean = false,
    val status: Int? = null,
    open val content: T? = null,
    open val message: String? = null,
    open val code: String? = null,
) {
    class Success<T>(override val content: T, status: Int) : ResponseWrapper<T>(
        isSuccess = true,
        status = status,
        content = content)

    class Error<T>(
        override val message: String,
        override val code: String?,
        status: Int? = null
    ) : ResponseWrapper<T>(
        hasError = true,
        status = status,
        isSessionExpired = status == SESSION_EXPIRED_CODE,
        message = message
    )

    companion object {
        private const val SESSION_EXPIRED_CODE = 401
        private const val UNEXPECTED_ERROR =
            "Ops! Não foi possível processar a sua solicitação."
        const val HTTP_ERROR_500 = 500

        fun <T> fromSuccess(response: Response<T>?) = Success(
            content = response!!.body(),
            status = response.code()
        )

        fun <T> fromError(response: Response<T>?): ResponseWrapper<T> {
            val baseUrl = response?.raw()?.request?.url.toString()
            val (code, message) = try {
                val type: Type = object : TypeToken<ErrorResponse>() {}.type
                val stream = response?.errorBody()!!.charStream()
                val error = Gson().fromJson<ErrorResponse>(stream, type)

                (error.code to (error.message ?: UNEXPECTED_ERROR))
            } catch (e: Exception) {
                Log.e("ResponseWrapper", ">>> Exception ${e.message}")
                (null to UNEXPECTED_ERROR)
            }
            return Error(message, code, response?.code())
        }

        fun <T> unexpected(status: Int? = null) = Error<T>(
            message = UNEXPECTED_ERROR,
            code = null,
            status = status
        )
    }
}

suspend inline fun <T> ResponseWrapper<T?>.then(
    crossinline onSuccess: OnSuccess<T>,
    crossinline onError: OnError
) {
    when (this) {
        is ResponseWrapper.Error -> onError(this.message)
        is ResponseWrapper.Success -> this.content?.let { onSuccess(it) }
    }
}

suspend inline fun <T> ResponseWrapper<T?>.then(
    crossinline onSuccess: OnSuccess<T>,
    crossinline onError: OnError,
    crossinline onFinish: OnFinish,
) {
    onFinish()
    when (this) {
        is ResponseWrapper.Error -> onError(this.message)
        is ResponseWrapper.Success -> this.content?.let { onSuccess(it) }
    }
}


suspend inline fun <T> ResponseWrapper<T?>.then(
    crossinline onSuccessNoContent: OnSuccessNoContent,
    crossinline onError: OnError
) {
    when (this) {
        is ResponseWrapper.Error -> onError(message)
        is ResponseWrapper.Success -> onSuccessNoContent()
    }
}

suspend inline fun <T> ResponseWrapper<T?>.then(
    crossinline onSuccessNoContent: OnSuccessNoContent,
    crossinline onError: OnError,
    crossinline onFinish: OnFinish,
) {
    onFinish()
    when (this) {
        is ResponseWrapper.Error -> onError(this.message)
        is ResponseWrapper.Success -> onSuccessNoContent()
    }
}


suspend inline fun <T> ResponseWrapper<T?>.then(
    crossinline onError: OnError,
    crossinline onFinish: OnFinish,
) {
    onFinish()
    when (this) {
        is ResponseWrapper.Error -> onError(this.message)
        is ResponseWrapper.Success -> {}
    }
}
