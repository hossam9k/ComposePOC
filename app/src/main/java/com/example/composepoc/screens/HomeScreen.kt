package com.example.composepoc

import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composepoc.activitycompose.ComposeDetailsMainActivity
import com.example.composepoc.screens.Dimensions
import com.example.composepoc.screens.LocalSpacing
import com.example.composepoc.ui.theme.POCTheme
import com.valentinilk.shimmer.LocalShimmerTheme
import com.valentinilk.shimmer.defaultShimmerTheme
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


@Composable
fun HomeScreen(scaffoldState: ScaffoldState,
               navigateToDetailScreen: (String, String,String) -> Unit){
    HomeBody(navigateToDetailScreen)


}

@Composable
fun HomeBody(
    navigateToDetailScreen: (String, String,String) -> Unit,
    spacing: Dimensions = LocalSpacing.current,
    isFromActivity:Boolean= false
){
        VerticalList(isFromActivity,navigateToDetailScreen)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun VerticalList(isFromActivity: Boolean,navigateToDetailScreen: (String, String,String) -> Unit){
    val context = LocalContext.current

    val groupedItems = movies.groupBy { it.name }


    LazyColumn(modifier = Modifier.fillMaxSize(),
    contentPadding = PaddingValues(bottom = 10.dp)
    ) {
        item {
            RowList(navigateToDetailScreen)
        }
        groupedItems.forEach{(name)->
            stickyHeader {
                Text(
                    text = name,
                    color = Color.White,
                    modifier = Modifier
                        .background(Color.Gray)
                        .padding(5.dp)
                        .fillMaxWidth()
                )
            }
            items(movies) { movie ->
                MovieCard(movie) {
                    val encodedUrl = URLEncoder.encode(it.thumbnail, StandardCharsets.UTF_8.toString())
                    navigateToDetailScreen(it.name,encodedUrl,it.desc)
                    if (isFromActivity){
                        val intent = Intent(context,ComposeDetailsMainActivity::class.java)
                        intent.putExtra("name",it.name)
                        intent.putExtra("desc",it.desc)
                        intent.putExtra("thumbnail",it.thumbnail)
                        context.startActivity(intent)
                    }
                }
            }
        }



    }
}

@Composable
fun RowList(navigateToDetailScreen: (String, String,String) -> Unit){
    LazyRow {
        items(movies) { movie ->
            var shimmerTheme by remember { mutableStateOf(defaultShimmerTheme) }
            CompositionLocalProvider(LocalShimmerTheme provides shimmerTheme) {
                MovieCard(movie) {
                    val encodedUrl = URLEncoder.encode(it.thumbnail, StandardCharsets.UTF_8.toString())
                    navigateToDetailScreen(it.name,encodedUrl,it.desc)
                }
            }

        }
    }
}

@Composable
fun MovieCard(movie: Movie, clickAction: (Movie) -> Unit) {

    Card(
        modifier = Modifier
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.Top)
            .clickable {
                clickAction(movie)
            },
        elevation = 8.dp,
        backgroundColor = Color.White,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            MoviePicture(movie, 70.dp)
            MovieContent(movie, Alignment.Start)
        }
    }
}







@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview
@Composable
fun ComposablePreview() {
    Scaffold{
        POCTheme(false) {
            HomeBody(navigateToDetailScreen = {_,_,_->})
        }
    }

}