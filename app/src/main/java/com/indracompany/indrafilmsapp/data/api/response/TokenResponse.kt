package com.indracompany.indrafilmsapp.data.api.response

import com.google.gson.annotations.SerializedName

data class TokenResponse(

    @SerializedName("statusCode")
    val statusCode: Int,

    @SerializedName("message")
    var message: String,

    @SerializedName("token")
    val token: String

)