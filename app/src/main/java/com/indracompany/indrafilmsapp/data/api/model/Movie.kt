package com.indracompany.indrafilmsapp.data.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(

    @SerializedName("poster_path")
    val posterPath: String? = null,

    @SerializedName("title")
    val title: String? = null,

    @SerializedName("vote_average")
    val voteAverage: Double? = null,

    @SerializedName("overview")
    val overview: String? = null,

    @SerializedName("release_date")
    val releaseDate: String? = null

) : Parcelable