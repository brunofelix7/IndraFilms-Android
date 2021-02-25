package com.indracompany.indrafilmsapp.data.api.model

import com.google.gson.annotations.SerializedName

data class ApiResponse<T>(
    @SerializedName("statusCode")
    val statusCode: Int,

    @SerializedName("message")
    var message: String,

    @SerializedName("body")
    val body: T?
)