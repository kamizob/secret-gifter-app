package com.example.secretgifter2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import com.example.secretgifter2.ui.screens.MainScreen
import com.example.secretgifter2.ui.theme.SecretGifter2Theme
import com.example.secretgifter2.viewmodel.MainViewModel
import androidx.compose.material3.Surface
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels  ()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SecretGifter2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(viewModel)
                }

            }
        }
    }
}