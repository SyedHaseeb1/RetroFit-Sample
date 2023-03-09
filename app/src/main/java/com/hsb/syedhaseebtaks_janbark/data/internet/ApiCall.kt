package com.hsb.syedhaseebtaks_janbark.data.internet

import com.hsb.syedhaseebtaks_janbark.data.models.SearchResult
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiCall {
    @GET("/")
    suspend fun searchMovies(
        @Query("apikey") apiKey: String,
        @Query("s") query: String,
        @Query("page") page: Int
    ): SearchResult
}