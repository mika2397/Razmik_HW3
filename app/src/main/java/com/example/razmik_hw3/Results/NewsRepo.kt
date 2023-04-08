package com.example.razmik_hw3.Results

import com.example.razmik_hw3.DataClasses.newsData
import com.example.razmik_hw3.newsResources.News
import retrofit2.create

class NewsRepo {
    suspend fun injectNews(): List<News> {
        return RetrofitHelper.getInstance().create(NewsApiService::class.java).getNews()
            .run {
                this.articles?.map {
                    News(name = it.title ?: "", author = it.author ?:"", it.title ?:"", imageUrl = it.urlToImage ?:"")
                } ?: listOf()
            }

    }
}