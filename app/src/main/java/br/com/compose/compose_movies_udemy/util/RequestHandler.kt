package br.com.compose.compose_movies_udemy.util

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.io.IOException
import java.net.UnknownHostException

typealias SuspendCall<T> = suspend () -> Response<T>?

object RequestHandler {

    private val successRange = 200..299
    private val errorRange = 400..400

    suspend fun <T: Any?> doRequest(
        handleSession: Boolean = true, call: SuspendCall<T>
    ): ResponseWrapper<T?> {
        return try {
            val response = withContext(Dispatchers.IO) { call.invoke() }
            val wrapper = when (response?.code()) {
                in successRange -> ResponseWrapper.fromSuccess(response)
                in errorRange -> ResponseWrapper.fromError(response)
                else -> ResponseWrapper.unexpected(response?.code())
            }
            return wrapper
        }  catch (e: UnknownHostException) {
            Log.e("RequestHandler", ">>> UnknownHostException ${e.message}")
            ResponseWrapper.unexpected()
        } catch (e: IOException) {
            Log.e("RequestHandler", ">>> IOException ${e.message}")
            ResponseWrapper.unexpected()
        } catch (e: Exception) {
            Log.e("RequestHandler", ">>> Exception ${e.message}")
            ResponseWrapper.unexpected()
        }
    }

    fun <T> ViewModel.doRequestAsync(handleSession: Boolean = true, call: SuspendCall<T>) =
        viewModelScope.doRequestAsync(handleSession, call)

    fun <T> CoroutineScope.doRequestAsync(handleSession: Boolean = true, call: SuspendCall<T>) =
        async { doRequest(handleSession, call) }
}
