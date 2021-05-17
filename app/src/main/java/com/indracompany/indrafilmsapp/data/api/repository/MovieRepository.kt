package com.indracompany.indrafilmsapp.data.api.repository

import com.indracompany.indrafilmsapp.data.api.ApiResult
import com.indracompany.indrafilmsapp.data.api.ApiService
import com.indracompany.indrafilmsapp.data.api.model.ApiResponse
import com.indracompany.indrafilmsapp.data.api.model.Movie
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val api: ApiService
) : IMovieRepository {

    override suspend fun fetchMovies(): ApiResult<ApiResponse<List<Movie>>> {
        return try {
            val response = api.fetchMovies()
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
