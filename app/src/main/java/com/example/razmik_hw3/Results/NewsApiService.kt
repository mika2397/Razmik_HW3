package com.example.razmik_hw3.Results

import com.example.razmik_hw3.DataClasses.NewsData
import com.example.razmik_hw3.newsResources.News
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface NewsApiService {

    @GET("/v2/top-headlines?country=us&apiKey=f4239382e2b44253be900285008892aa")
    suspend fun getNews(
        @Query("category") category: String = "",
        @Query("q") q: String = ""
    ): NewsData
}