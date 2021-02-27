package com.indracompany.indrafilmsapp.data.api.repository

import com.indracompany.indrafilmsapp.data.api.RetrofitInstance.apiService
import com.indracompany.indrafilmsapp.data.api.model.ApiResponse
import com.indracompany.indrafilmsapp.data.api.model.Token
import com.indracompany.indrafilmsapp.data.api.model.User
import retrofit2.Response

class UserRepository : IUserRepository {

    override suspend fun userLogin(user: User): Response<ApiResponse<Token>> = apiService.login(user)

}