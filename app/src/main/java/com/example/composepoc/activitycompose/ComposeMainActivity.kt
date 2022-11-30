package com.example.composepoc.activitycompose

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.sp
import com.example.composepoc.HomeBody
import com.example.composepoc.HomeScreen
import com.example.composepoc.R
import com.example.composepoc.ui.theme.POCTheme

class ComposeMainActivity : AppCompatActivity() {

    lateinit var composeView: ComposeView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compose_main)

        composeView = findViewById(R.id.compose_view)

        composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

            setContent {
                // In Compose world
                POCTheme {
                    Box(
                        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                    ) {
                       // Greeting("Hello Geeks for geeks")
                        HomeBody(isFromActivity = true, navigateToDetailScreen = {_,_,_->})
                    }
                }
            }
        }
    }

    @Composable
    fun Greeting(text: String) {
        Text(
            text = "$text!",
            color = Color(0xFF0F9D58),
            fontStyle = FontStyle.Italic,
            fontSize = 30.sp
        )

    }
}