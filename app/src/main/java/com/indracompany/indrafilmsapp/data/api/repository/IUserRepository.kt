package com.indracompany.indrafilmsapp.data.api.repository

import com.indracompany.indrafilmsapp.data.api.model.ApiResponse
import com.indracompany.indrafilmsapp.data.api.model.Token
import com.indracompany.indrafilmsapp.data.api.model.User
import retrofit2.Response

interface IUserRepository {
    suspend fun userLogin(user: User) : Response<ApiResponse<Token>>
}