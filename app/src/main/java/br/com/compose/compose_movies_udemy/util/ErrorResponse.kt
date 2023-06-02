package br.com.compose.compose_movies_udemy.util

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("message") val message: String?,
    @SerializedName("code") val code: String)
