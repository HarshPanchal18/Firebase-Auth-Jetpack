package com.example.firebase_auth_jetpack.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firebase_auth_jetpack.view.LoginScreen
import com.example.firebase_auth_jetpack.view.WelcomeScreen
import com.example.firebase_auth_jetpack.viewmodel.AuthViewModel

@Composable
fun NavigationGraph(
    viewModel: AuthViewModel,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUTE_WELCOME
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(ROUTE_WELCOME) { WelcomeScreen(navController) }
        composable(ROUTE_LOGIN) { LoginScreen(navController, viewModel) }
        composable(ROUTE_SIGNUP) {
            //SignUpScreen(navController,viewModel)
        }
        composable(ROUTE_HOME) {
            //HomeScreen(navController,viewModel)
        }
    }
}
