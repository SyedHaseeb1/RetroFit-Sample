package com.hsb.syedhaseebtaks_janbark.data.internet

import com.hsb.syedhaseebtaks_janbark.data.models.Movies
import com.hsb.syedhaseebtaks_janbark.utils.apiUrl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitApiCall {
    private val apiCall: ApiCall by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(apiUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ApiCall::class.java)
    }

    suspend fun searchMovies(apiKey: String, query: String, page: Int): List<Movies> {
        val response = apiCall.searchMovies(apiKey, query, page)
        return response.Search
    }
}