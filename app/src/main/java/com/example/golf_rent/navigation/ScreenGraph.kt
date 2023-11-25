package com.example.golf_rent.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.golf_rent.view.AdminPanel
import com.example.golf_rent.view.Authorization
import com.example.golf_rent.view.Catalog
import com.example.golf_rent.view.Meeting
import com.example.golf_rent.view.Registration

@Composable
fun ScreenGraph(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.CatalogScreen.route){
        composable(Screens.MeetingScreen.route){
            Meeting(navController)
        }
        composable(Screens.AuthorizationScreen.route){
            Authorization(navController)
        }
        composable(Screens.RegistrationScreen.route){
            Registration(navHostController = navController)
        }
        composable(Screens.AdminPanelScreen.route){
            AdminPanel()
        }
        composable(Screens.CatalogScreen.route){
            Catalog()
        }
    }
}