package com.example.razmik_hw3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.razmik_hw3.DataClasses.NewsData
import com.example.razmik_hw3.UI.SearchBar
import com.example.razmik_hw3.newsResources.News
import com.example.razmik_hw3.UI.FilterExpand
import android.util.Log
import androidx.compose.foundation.background
import androidx.navigation.NavHost
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.razmik_hw3.DataClasses.Filter
import com.example.razmik_hw3.Screens.NewsDetailScreen
import com.example.razmik_hw3.Screens.NewsScreen
import com.example.razmik_hw3.Screens.ScreensData

class MainActivity : AppCompatActivity() {
    private val dataLoaderViewModel: DataLoaderViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        var categoryText = ""
        var searchText = ""
        super.onCreate(savedInstanceState)
        dataLoaderViewModel.loadNews()
        dataLoaderViewModel.newsList.observe(this) { newsList, ->



            setContent {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = ScreensData.MainScreen.routeToPage
                ) {

                    composable(
                        route = ScreensData.MainScreen.routeToPage) {
                        NewsScreen(
                            navController = navController,
                            newsListResult = newsList,
                            onRefresh = dataLoaderViewModel::loadNews,
                            onSelectFilter = {
                                categoryText = it
                                dataLoaderViewModel.loadFilteredNews(it, searchText)
                            },
                            onSearch = {
                                searchText = it
                                dataLoaderViewModel.loadFilteredNews(categoryText,it)
                            }

                        )
                    }

                    composable(
                        route = ScreensData.DetailScreen.routeToPage + "/{id}",
                        arguments = listOf(navArgument(name = "id") {
                            type = NavType.IntType
                        }
                        )) {
                            dataVal ->
                        val id = dataVal.arguments?.getInt("id")

                        id?.let {
                            newsList[it]
                        }?.let {
                            NewsDetailScreen(navController, news = it)
                        }

                    }
                }
            }
        }
    }
}
