package com.indracompany.indrafilmsapp.data.api.repository

import com.indracompany.indrafilmsapp.data.api.RetrofitInstance.apiService
import com.indracompany.indrafilmsapp.data.api.model.ApiResponse
import com.indracompany.indrafilmsapp.data.api.model.Movie
import retrofit2.Response

class MovieRepository : IMovieRepository {

    override suspend fun fetchMovies(): Response<ApiResponse<List<Movie>>> = apiService.fetchMovies()

}
