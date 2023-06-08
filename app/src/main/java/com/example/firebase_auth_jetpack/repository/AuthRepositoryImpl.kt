package com.example.firebase_auth_jetpack.repository

import com.example.firebase_auth_jetpack.utils.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth : FirebaseAuth
): AuthRepository {
    override suspend fun loginUser(email: String, password: String): Resource<FirebaseUser> {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            Resource.Success(result.user!!)
        } catch(e: Exception) {
            Resource.Failure(e)
        }
    }

    override suspend fun registerUser(name: String, email: String, password: String): Resource<FirebaseUser> {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            result?.user?.updateProfile(UserProfileChangeRequest.Builder().setDisplayName(name).build())?.await()
            Resource.Success(result.user!!)
        } catch(e: Exception) {
            Resource.Failure(e)
        }
    }

    override val currentUser: FirebaseUser?
    get() = firebaseAuth.currentUser

    override fun logout() {
        firebaseAuth.signOut()
    }
}
