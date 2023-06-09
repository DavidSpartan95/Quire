package com.example.quire.screens.navGraph

sealed class Screen(val route: String){
    object Home:Screen(route = "home_screen")
    object Tasks:Screen(route = "task_screen")
    object TaskItem:Screen(route = "task_item_screen/{specificNote}/{noteIndex}/{folders}")
    object NewTaskItem: Screen(route = "task_item_screen")
    object Folder:Screen(route = "folder_screen")

}
