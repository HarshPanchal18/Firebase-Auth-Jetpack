package com.example.firebase_auth_jetpack.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.savedstate.SavedStateRegistryOwner
import com.example.firebase_auth_jetpack.data.AuthRepository
import com.example.firebase_auth_jetpack.state.SignInState
import com.example.firebase_auth_jetpack.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val savedStateHandle: SavedStateHandle,
    //private val owner: SavedStateRegistryOwner,
) : ViewModel() {

    private companion object {
        const val SAVED_STATE_REGISTRY_OWNER_KEY = "androidx.lifecycle.savedstate.vm.tag"
    }

    private val _signUpState = Channel<SignInState>()
    val signUpState = _signUpState.receiveAsFlow()

    fun registerUser(email: String, password: String) = viewModelScope.launch {
        repository.loginUser(email, password).collect { result ->
            when(result) {
                is Resource.Success -> {
                    _signUpState.send(SignInState(isSuccess = "SignIn Success"))
                }
                is Resource.Error -> {
                    _signUpState.send(SignInState(isError = result.message))
                }
                is Resource.Loading -> {
                    _signUpState.send(SignInState(isLoading = true))
                }
            }
        }
    }
}
