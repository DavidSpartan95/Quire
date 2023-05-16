package com.example.quire.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.quire.dataBase.UserRepository
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.quire.ui.theme.components.HomeScreenContent

@Composable
fun HomeScreen(navController: NavController, userRepository: UserRepository) {

    Surface(Modifier.fillMaxSize(),
            color = Color(238,238,238)
    ) {

        HomeScreenContent(navController = navController, userRepository = userRepository)
    }

}