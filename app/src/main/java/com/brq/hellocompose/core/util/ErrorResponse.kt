package com.brq.hellocompose.core.util

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("message") val message: String?,
    @SerializedName("code") val code: String)
