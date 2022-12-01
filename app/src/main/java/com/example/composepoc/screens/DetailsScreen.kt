package com.example.composepoc

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.composepoc.screens.Dimensions
import com.example.composepoc.screens.LocalSpacing
import com.example.composepoc.ui.theme.POCTheme

@Composable
fun DetailsScreen(
    scaffoldState: ScaffoldState,
    name: String,
    thumbnail: String,
    desc: String,
    onNavigateUp: () -> Unit
){

    val spacing: Dimensions = LocalSpacing.current
    Spacer(modifier = Modifier.height(spacing.spaceExtraLarge))


    DetailsBody(name,thumbnail,desc)
}

@Composable
fun DetailsBody(name: String,
                  thumbnail: String,
                  desc:String){
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            Card(
                modifier = Modifier
                    .height(200.dp)
                    .padding(start = 16.dp, top = 30.dp, end = 16.dp),
                backgroundColor = Color.Transparent,
                shape = RoundedCornerShape(15.dp),
                elevation = 4.dp,
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Gray)
                    //   .aspectRatio(1f)
                    // .align(Alignment.Center)
                ){
                    AndroidView(modifier = Modifier.fillMaxSize(), factory = {
                        val view = LayoutInflater.from(it).inflate(R.layout.my_view, null, false)
                        val button = view.findViewById<Button>(R.id.button2)
                        val text = view.findViewById<TextView>(R.id.text2)
                        button.setOnClickListener {
                            text.text = "replacing text from Compose Function"
                        }

                        view
                    })
                }

            }

        }
        item {
            Card(
                modifier = Modifier
                    .height(200.dp)
                    .padding(start = 16.dp, top = 30.dp, end = 16.dp),
                backgroundColor = Color.Transparent,
                shape = RoundedCornerShape(15.dp),
                elevation = 4.dp,
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Gray)
                    //   .aspectRatio(1f)
                    // .align(Alignment.Center)
                ){
                    Column(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        val movie = Movie(id = 0,name = name,thumbnail=thumbnail, desc = desc)
                        MovieCardDetails(movie = movie)

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            //  CardExpirationDate(Modifier.weight(1f))
                            //CardHolderName(Modifier.weight(1f))
                        }


                    }
                }
            }
        }

    }
}

@Composable
fun MovieCardDetails(movie: Movie) {
    Card(
        modifier = Modifier
            .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
            .fillMaxSize()
            .wrapContentHeight(align = Alignment.Top),
        elevation = 8.dp,
        backgroundColor = Color.White,
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Log.d("thumbnail,",movie.thumbnail.toString())
            MoviePictureDetails(movie, 100.dp)
            MovieContent(movie, Alignment.Start)
        }
    }
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview
@Composable
fun ComposableDetailsPreview() {
    Scaffold{
        POCTheme(false) {
            DetailsBody("","","")

        }
        }
    }



@Preview
@Composable
fun previewTest(){
            AndroidView(factory = {
                val view = LayoutInflater.from(it).inflate(R.layout.my_view, null, false)
                val button = view.findViewById<Button>(R.id.button2)
                val text = view.findViewById<TextView>(R.id.text2)
                button.setOnClickListener {
                    text.text = "replacing text from Compose Function"
                }

                view
            })
}