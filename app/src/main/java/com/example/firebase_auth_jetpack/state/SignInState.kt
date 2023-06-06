package com.example.firebase_auth_jetpack.state

data class SignInState(
    val isLoading: Boolean = false,
    val isSuccess: String? = "",
    val isError: String? = "",
)
