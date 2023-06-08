package com.example.firebase_auth_jetpack.repository

import com.example.firebase_auth_jetpack.utils.Resource
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    val currentUser: FirebaseUser?
    suspend fun loginUser(email: String, password: String): Resource<FirebaseUser>
    suspend fun registerUser(name: String, email: String, password:String): Resource<FirebaseUser>
    fun logout()
}
