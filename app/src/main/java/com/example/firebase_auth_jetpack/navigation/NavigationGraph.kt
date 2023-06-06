package com.example.firebase_auth_jetpack.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firebase_auth_jetpack.screen.SignInScreen
import com.example.firebase_auth_jetpack.screen.SignUpScreen

@Composable
fun NavigationGraph(navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = Screens.SignUpScreen.route) {
        composable(Screens.SignInScreen.route) {
            SignInScreen()
        }
        composable(Screens.SignUpScreen.route) {
            SignUpScreen()
        }
    }
}
