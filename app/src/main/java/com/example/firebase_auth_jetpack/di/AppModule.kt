package com.example.firebase_auth_jetpack.di

import com.example.firebase_auth_jetpack.repository.AuthRepository
import com.example.firebase_auth_jetpack.repository.AuthRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun providesFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    fun providesRepositoryImpl(impl: AuthRepositoryImpl): AuthRepository = impl
}
