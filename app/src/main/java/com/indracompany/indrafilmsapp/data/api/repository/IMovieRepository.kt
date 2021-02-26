package com.indracompany.indrafilmsapp.data.api.repository

import com.indracompany.indrafilmsapp.data.api.model.ApiResponse
import com.indracompany.indrafilmsapp.data.api.model.Movie
import retrofit2.Response

interface IMovieRepository {
    suspend fun fetchMovies() : Response<ApiResponse<List<Movie>>>
}