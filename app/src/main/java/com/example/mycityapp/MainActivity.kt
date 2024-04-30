package com.example.mycityapp

import MycityViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mycityapp.ui.MycityApp
import com.example.mycityapp.ui.theme.MyCityAppTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyCityAppTheme {
                val viewModel: MycityViewModel = viewModel()
                Surface {
                    val windowSize = calculateWindowSizeClass(this)
                    MycityApp(
                        windowSize = windowSize.widthSizeClass,
                                viewModel)
                }
            }
        }
    }
}

