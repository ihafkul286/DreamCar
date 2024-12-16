package com.example.dreamcar.activity

import android.app.Activity
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.dreamcar.screens.AboutScreen
import com.example.dreamcar.screens.DetailFavScreen
import com.example.dreamcar.screens.DetailHorizontalScreen
import com.example.dreamcar.screens.DetailItemScreen
import com.example.dreamcar.screens.ElemListScreen
import com.example.dreamcar.screens.ElemListScreenHorizontal
import com.example.dreamcar.screens.FavListScreen
import com.example.dreamcar.screens.ProfileScreen
import com.example.dreamcar.utils.getWindowSizeClass

@Composable
fun ElemListActivityContent() {
    val navController = rememberNavController()

    val windowSize = getWindowSizeClass(LocalContext.current as Activity)

    NavHost(
        navController = navController,
        startDestination = "elements_list"
    ) {
        composable("elements_list") {
            when (windowSize) {
                WindowWidthSizeClass.Compact -> {
                    ElemListScreen(
                        onElementClick = { elementName: String ->
                            navController.navigate("element_detail/$elementName")
                        },
                        onFavoritesClick = { navController.navigate("favorites_list") },
                        onProfileClick = { navController.navigate("profile") },
                        onAboutClick = { navController.navigate("about") }
                    )
                }
                else -> {
                    ElemListScreenHorizontal(
                        onElementClick = { elementName: String ->
                            navController.navigate("element_detail/$elementName")
                        },
                        onFavoritesClick = { navController.navigate("favorites_list") },
                        onProfileClick = { navController.navigate("profile") },
                        onAboutClick = { navController.navigate("about") }
                    )
                }
            }
        }

        composable("favorites_list") {
            FavListScreen(
                onFavoriteClick = { favoriteName: String ->
                    navController.navigate("favorite_detail/$favoriteName")
                }
            )
        }

        composable("profile") {
            ProfileScreen()
        }

        composable("about") {
            AboutScreen()
        }

        composable(
            route = "element_detail/{elementName}",
            arguments = listOf(navArgument("elementName") { type = NavType.StringType })
        ) { backStackEntry ->
            val elementName = backStackEntry.arguments?.getString("elementName") ?: ""
            when (windowSize) {
                WindowWidthSizeClass.Compact -> {
                    DetailItemScreen(name = elementName)
                }
                else -> {
                    DetailHorizontalScreen(name = elementName)
                }
            }
        }

        composable(
            route = "favorite_detail/{favoriteName}",
            arguments = listOf(navArgument("favoriteName") { type = NavType.StringType })
        ) { backStackEntry ->
            val favoriteName = backStackEntry.arguments?.getString("favoriteName") ?: ""
            when (windowSize) {
                WindowWidthSizeClass.Compact -> {
                    DetailFavScreen(name = favoriteName)
                }
                else -> {
                    DetailHorizontalScreen(name = favoriteName)
                }
            }
        }
    }
}



