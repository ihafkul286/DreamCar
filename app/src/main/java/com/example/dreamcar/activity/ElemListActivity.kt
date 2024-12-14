package com.example.dreamcar.activity

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.compose.DreamCarTheme
import com.example.dreamcar.screens.AboutScreen
import com.example.dreamcar.screens.DetailFavScreen
import com.example.dreamcar.screens.DetailHorizontalScreen
import com.example.dreamcar.screens.DetailItemScreen
import com.example.dreamcar.screens.ElemListScreen
import com.example.dreamcar.screens.ElemListScreenHorizontal
import com.example.dreamcar.utils.getWindowSizeClass

class ElemListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DreamCarTheme {
                Contenido()
            }
        }
    }
    @Composable
    fun Contenido(){
        val windowSizeClass = getWindowSizeClass(LocalContext.current as Activity)
        when (windowSizeClass) {
            WindowWidthSizeClass.Compact -> { DetailFavScreen() }
            else -> { DetailHorizontalScreen() }
        }
    }
}

