package com.brq.hellocompose.core.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.io.IOException
import java.net.UnknownHostException

typealias SuspendCall<T> = suspend () -> Response<T>?

object RequestHandler {

    private val successRange = 200..299
    private val errorRange = 400..400

    suspend fun <T : Any?> doRequest(
        call: SuspendCall<T>
    ): ResponseWrapper<T?> {
        return try {
            val response = withContext(Dispatchers.IO) { call.invoke() }
            val wrapper = when (response?.code()) {
                in successRange -> ResponseWrapper.fromSuccess(response)
                in errorRange -> ResponseWrapper.fromError(response)
                else -> ResponseWrapper.unexpected(response?.code())
            }
            return wrapper
        } catch (e: UnknownHostException) {
            ResponseWrapper.unexpected()
        } catch (e: IOException) {
            ResponseWrapper.unexpected()
        } catch (e: Exception) {
            ResponseWrapper.unexpected()
        }
    }
}