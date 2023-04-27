package com.example.razmik_hw3

import android.util.Log
import androidx.lifecycle.*

import com.example.razmik_hw3.DataClasses.NewsData
import com.example.razmik_hw3.Results.NewsRepo
import com.example.razmik_hw3.newsResources.News
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class DataLoaderViewModel : ViewModel(){

    private val refreshTrigger = MutableLiveData<Boolean>()

    private val newsListBase: MutableLiveData<List<News>> = MutableLiveData()
    val newsList: LiveData<List<News>> = newsListBase

    fun loadNews() {

            viewModelScope.launch {
                try {
                    val resultValues = NewsRepo().injectNews()
                    newsListBase.postValue(resultValues)
                }
                catch (e: Exception) {
                    Log.d("Error found", "Error occurred during network request")
                }
        }
    }

    fun loadFilteredNews(category:String,searchData:String) {
        viewModelScope.launch {
            try {
                val resultValues = NewsRepo().injectNews(category,searchData)
                newsListBase.postValue(resultValues)
            }
            catch (e: Exception) {
                Log.d("Error found", "Error occurred during filtered network request")
            }

        }
    }

}