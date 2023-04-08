package com.example.razmik_hw3

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.razmik_hw3.DataClasses.newsData
import com.example.razmik_hw3.Results.NewsRepo
import com.example.razmik_hw3.newsResources.News
import kotlinx.coroutines.launch

class DataLoaderViewModel : ViewModel(){

    private val newsListBase: MutableLiveData<List<News>> = MutableLiveData()
    val newsList: LiveData<List<News>> = newsListBase

    fun loadNews() {
            viewModelScope.launch {
                val resultValues = NewsRepo().injectNews()
                newsListBase.postValue(resultValues)
        }
    }

}