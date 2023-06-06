package com.example.firebase_auth_jetpack.data

import com.example.firebase_auth_jetpack.util.Resource
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun loginUser(email: String, password: String): Flow<Resource<AuthResult>>
    fun registerUser(email: String, password:String): Flow<Resource<AuthResult>>
}
