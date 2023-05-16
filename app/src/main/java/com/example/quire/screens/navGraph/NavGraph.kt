package com.example.quire.screens.navGraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.quire.dataBase.UserRepository
import com.example.quire.screens.HomeScreen
import com.example.quire.screens.TaskItemScreen
import com.example.quire.screens.TaskScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController, userRepository: UserRepository
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
    ){
        composable(
            route = Screen.Home.route
        ){
            HomeScreen(navController = navController, userRepository = userRepository)
        }
        composable(
            route = Screen.Tasks.route
        ){
            TaskScreen(navController = navController, userRepository = userRepository)
        }
        composable(
            route = Screen.TaskItem.route
        ){
            TaskItemScreen(navController = navController, userRepository = userRepository)
        }
    }

}