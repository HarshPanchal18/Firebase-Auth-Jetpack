package com.example.firebase_auth_jetpack.navigation

sealed class Screens(val route: String) {
    object SignInScreen: Screens("SignInScreen")
    object SignUpScreen: Screens("SignUpScreen")
}
