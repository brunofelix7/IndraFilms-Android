package com.indracompany.indrafilmsapp.data.api.repository

import com.indracompany.indrafilmsapp.data.api.ApiService
import com.indracompany.indrafilmsapp.data.api.model.ApiResponse
import com.indracompany.indrafilmsapp.data.api.model.Movie
import retrofit2.Response

class MovieRepository(private val api: ApiService) : IMovieRepository {

    override suspend fun fetchMovies() : Response<ApiResponse<List<Movie>>> = api.fetchMovies()

}
