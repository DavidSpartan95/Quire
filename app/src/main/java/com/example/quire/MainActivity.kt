package com.example.quire

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.quire.dataBase.AppDatabase
import com.example.quire.dataBase.UserRepository
import com.example.quire.screens.navGraph.SetupNavGraph
import com.example.quire.ui.theme.QuireTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var navController: NavHostController
        super.onCreate(savedInstanceState)
        val db = AppDatabase.getInstance(applicationContext)
        val userRepository = UserRepository(db, lifecycleScope)
        setContent {
            QuireTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    navController = rememberNavController()
                    SetupNavGraph(navController = navController, userRepository = userRepository)
                }
            }
        }
    }
}
