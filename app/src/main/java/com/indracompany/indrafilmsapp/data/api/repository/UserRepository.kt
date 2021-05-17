package com.indracompany.indrafilmsapp.data.api.repository

import com.indracompany.indrafilmsapp.data.api.ApiResult
import com.indracompany.indrafilmsapp.data.api.ApiService
import com.indracompany.indrafilmsapp.data.api.model.ApiResponse
import com.indracompany.indrafilmsapp.data.api.model.Token
import com.indracompany.indrafilmsapp.data.api.model.User
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val api: ApiService
) : IUserRepository {

    override suspend fun userLogin(user: User): ApiResult<ApiResponse<Token>> {
        return try {
            val response = api.login(user)
            val result = response.body()

            if (response.isSuccessful && result?.body != null) {
                ApiResult.Success(result)
            } else {
                ApiResult.Error(response.errorBody().toString())
            }
        } catch (e: Exception) {
            ApiResult.Error(e.message ?: "An error ocurred.")
        }
    }
}