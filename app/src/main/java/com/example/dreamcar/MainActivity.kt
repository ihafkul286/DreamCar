package com.example.dreamcar

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavType
import com.example.dreamcar.ui.screens.AboutScreen
import com.example.dreamcar.ui.screens.detailscreen.DetailFavScreen
import com.example.dreamcar.ui.screens.detailscreen.DetailHorizontalScreen
import com.example.dreamcar.ui.screens.detailscreen.DetailItemScreen
import com.example.dreamcar.ui.screens.elemlist.ElemListScreen
import com.example.dreamcar.ui.screens.FavListScreen
import com.example.dreamcar.ui.screens.ProfileScreen
import com.example.dreamcar.utils.getWindowSizeClass
import com.example.compose.DreamCarTheme
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import kotlinx.coroutines.flow.map
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.dreamcar.data.UserPreferencesRepository
import com.example.dreamcar.ui.screens.elemlist.ElemListScreenHorizontal


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DreamCarTheme {
                Contenido()
            }
        }
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    fun Contenido() {
        val navController = rememberNavController()
        val windowSize = getWindowSizeClass(LocalContext.current as Activity)
        val currentRoute by navController.currentBackStackEntryFlow
            .map { it.destination.route }
            .collectAsState(initial = null)

        DreamCarTheme {
            Scaffold(
                bottomBar = {
                    if (currentRoute in listOf(
                            "elements_list",
                            "favorites_list",
                            "profile",
                            "about"
                        )
                    ) {
                        BottomNavigationBar(
                            navController = navController,
                            currentRoute = currentRoute
                        )
                    }
                }
            ) {
                NavHost(
                    navController = navController,
                    startDestination = "elements_list",
                    modifier = Modifier.fillMaxSize()
                ) {
                    composable("elements_list") {
                        when (windowSize) {
                            WindowWidthSizeClass.Compact -> {
                                ElemListScreen(
                                    onElementClick = { car: Car ->
                                        navController.navigate("element_detail/${car.hashCode()}")
                                    },
                                    onFavoritesClick = { navController.navigate("favorites_list") },
                                    onProfileClick = { navController.navigate("profile") },
                                    onAboutClick = { navController.navigate("about") }
                                )
                            }
                            else -> {
                                ElemListScreenHorizontal(
                                    onElementClick = { car: Car ->
                                        navController.navigate("element_detail/${car.hashCode()}")
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
                            onFavoriteClick = { car ->
                                navController.navigate("element_detail/${car.hashCode()}")
                            }
                        )
                    }


                    composable("profile") {
                        ProfileScreen(
                            userPreferencesRepository = UserPreferencesRepository(LocalContext.current)
                        )
                    }

                    composable("about") {
                        AboutScreen()
                    }

                    composable(
                        route = "element_detail/{carId}",
                        arguments = listOf(navArgument("carId") { type = NavType.IntType })
                    ) { backStackEntry ->
                        val carId = backStackEntry.arguments?.getInt("carId")
                        val car = Datasource.carList().find { it.hashCode() == carId }
                        if (car != null) {
                            when (windowSize) {
                                WindowWidthSizeClass.Compact -> {
                                    DetailItemScreen(
                                        car = car,
                                        name = "${car.make} ${car.model}",
                                        navController = navController,
                                        backStackEntry = backStackEntry
                                    )
                                }
                                else -> {
                                    val favoriteName = ""
                                    DetailHorizontalScreen(car = car, name = favoriteName) // Pasa el coche completo aquí
                                }
                            }
                        }
                    }


                    composable(
                        route = "favorite_detail/{favoriteName}",
                        arguments = listOf(navArgument("favoriteName") {
                            type = NavType.StringType
                        })
                    ) { backStackEntry ->
                        val favoriteName = backStackEntry.arguments?.getString("favoriteName") ?: ""
                        when (windowSize) {
                            WindowWidthSizeClass.Compact -> {
                                DetailFavScreen(name = favoriteName)
                            }
                            else -> {
                                DetailHorizontalScreen(
                                    name = favoriteName,
                                    car = TODO()
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun BottomNavigationBar(navController: NavController, currentRoute: String?) {
        val items = listOf(
            NavigationItem("elements_list", "Home", Icons.Default.Home),
            NavigationItem("favorites_list", "Favorites", Icons.Default.Favorite),
            NavigationItem("profile", "Profile", Icons.Default.Person),
            NavigationItem("about", "About", Icons.Default.Info)
        )

        NavigationBar {
            items.forEach { item ->
                NavigationBarItem(
                    icon = { Icon(imageVector = item.icon, contentDescription = item.label) },
                    label = { Text(text = item.label) },
                    selected = currentRoute == item.route,
                    onClick = {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }

    data class NavigationItem(
        val route: String,
        val label: String,
        val icon: ImageVector
    )
}

