package com.example.composepoc.screens

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composepoc.ui.theme.POCTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            POCTheme {
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    scaffoldState = scaffoldState
                ){
                    NavHost(
                        navController = navController,
                        startDestination = Route.HOME
                    ){
                        composable(Route.HOME) {
                            HomeScreen(
                                scaffoldState = scaffoldState,
                                navigateToDetailScreen = { name, thumbnail, desc ->
                                    navController.navigate(
                                        Route.DETAILS +
                                                "/$name" +
                                                "/$thumbnail"+
                                                "/$desc"
                                    )
                                }
                            )
                        }
                        composable(
                            route = Route.DETAILS + "/{name}/{thumbnail}/{desc}",
                            arguments = listOf(
                                navArgument("name"){
                                    type = NavType.StringType
                                },
                                navArgument("thumbnail"){
                                    type = NavType.StringType
                                },
                                navArgument("desc"){
                                    type = NavType.StringType
                                },
                        )
                        ){
                            val name = it.arguments?.getString("name") ?: ""
                            val thumbnail = it.arguments?.getString("thumbnail")!!
                            val desc = it.arguments?.getString("desc")!!


                            DetailsScreen(
                                scaffoldState = scaffoldState,
                                name = name,
                                thumbnail = thumbnail,
                                desc = desc,
                                onNavigateUp = {
                                    navController.navigateUp()
                                }
                                )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    POCTheme {
    }
}