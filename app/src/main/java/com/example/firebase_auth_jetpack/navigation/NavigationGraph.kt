package com.example.firebase_auth_jetpack.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavigationGraph(navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = Screens.SignUpScreen.route) {
        composable(Screens.SignInScreen.route) {}
        composable(Screens.SignUpScreen.route) {}
    }
}
