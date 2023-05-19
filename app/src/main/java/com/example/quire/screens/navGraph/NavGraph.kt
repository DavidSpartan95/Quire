package com.example.quire.screens.navGraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.quire.dataBase.UserRepository
import com.example.quire.dataBase.note.Note
import com.example.quire.screens.HomeScreen
import com.example.quire.screens.TaskItemScreen
import com.example.quire.screens.TaskScreen
import com.google.gson.Gson

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
            route = Screen.NewTaskItem.route
        ){
            TaskItemScreen(navController = navController, userRepository = userRepository)
        }
        composable(
            route = Screen.TaskItem.route,
            arguments = listOf(navArgument("specificNote") {
                type = NavType.StringType
            }, navArgument("noteIndex"){
                type = NavType.IntType
            })
        ) { backStackEntry ->
           backStackEntry.arguments?.getString("specificNote")?.let {
               json ->
               println("InsideNavGrah")
               val note = Gson().fromJson(json,Note::class.java)

               TaskItemScreen(navController = navController, userRepository = userRepository, specificNote = note, noteIndex = backStackEntry.arguments?.getInt("noteIndex"))
           }

        }
    }
}