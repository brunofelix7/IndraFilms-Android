package com.indracompany.indrafilmsapp.data.api.response

import com.google.gson.annotations.SerializedName

data class FilmResponse(

    @SerializedName("poster_path")
    val posterPath: String?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("vote_average")
    val voteAverage: Double?,

    @SerializedName("overview")
    val overview: String?,

    @SerializedName("release_date")
    val releaseDate: String?

)