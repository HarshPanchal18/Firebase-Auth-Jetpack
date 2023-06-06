package com.example.firebase_auth_jetpack.di

import android.app.Application
import androidx.savedstate.SavedStateRegistryOwner
import com.example.firebase_auth_jetpack.data.AuthRepository
import com.example.firebase_auth_jetpack.data.AuthRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providesFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun providesRepositoryImpl(auth: FirebaseAuth): AuthRepository {
        return AuthRepositoryImpl(auth)
    }

    /*@Provides
    @Singleton
    fun provideSavedStateRegistryOwner(application: Application): SavedStateRegistryOwner {
        return application as SavedStateRegistryOwner
    }*/
}
