package com.example.razmik_hw3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.razmik_hw3.DataClasses.newsData
import com.example.razmik_hw3.newsResources.News

class MainActivity : AppCompatActivity() {
    lateinit var searchView: SearchView
    private val dataLoaderViewModel: DataLoaderViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataLoaderViewModel.loadNews()
        dataLoaderViewModel.newsList.observe(this) { newsList ->
            setContent {
                NewsList(newsList = newsList)
            }

        }

    }
}

@Composable
fun NewsList(newsList: List<News>) {
    LazyColumn {
        items(newsList) {   news ->
            NewsCard(news)
        }
    }
}

@Composable
fun NewsCard(news: News) {
    Column {
        Text(text = news.title,
            style = TextStyle(fontSize = 24.sp),
            textAlign =  TextAlign.Center,
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth())

        AsyncImage(

            model = news.imageUrl,
            error = painterResource(id = R.drawable.errorimg),
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth(),
            contentDescription = "",
            placeholder = painterResource(id = R.drawable.loadingimg)
        )
        Text(text = news.author,
            style = TextStyle(fontSize = 12.sp),
            textAlign =  TextAlign.Center,
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth())

        Divider(
            color = Color.Black,
            modifier = Modifier.padding(vertical = 20.dp)
        )
    }
}