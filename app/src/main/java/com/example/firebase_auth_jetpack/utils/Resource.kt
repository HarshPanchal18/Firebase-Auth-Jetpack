package com.example.firebase_auth_jetpack.utils

sealed class Resource<out R> {
    class Success<R>(val result: R) : Resource<R>()
    class Failure(val exception: Exception) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
}
