package com.example.firebase_auth_jetpack.view

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.firebase_auth_jetpack.R
import com.example.firebase_auth_jetpack.component.AuthHeader
import com.example.firebase_auth_jetpack.navigation.ROUTE_HOME
import com.example.firebase_auth_jetpack.navigation.ROUTE_LOGIN
import com.example.firebase_auth_jetpack.navigation.ROUTE_SIGNUP
import com.example.firebase_auth_jetpack.ui.theme.spacing
import com.example.firebase_auth_jetpack.utils.Resource
import com.example.firebase_auth_jetpack.viewmodel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SignUpScreen(
    navController: NavHostController,
    viewModel: AuthViewModel?
) {

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val signUpFlow = viewModel?.registerFlow?.collectAsState()
    val context = LocalContext.current
    val kc = LocalSoftwareKeyboardController.current
    val callBack = { kc?.hide() }
    val focusManager = LocalFocusManager.current

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text("Signup", color = Color.White)
            },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(ROUTE_LOGIN) {
                            popUpTo(ROUTE_SIGNUP) { inclusive = true }
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                })
        }) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            item {
                ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                    val (refHeader, refName, refEmail, refPassword, refButtonSignUp, refTextSignUp, refLoader) = createRefs()
                    val spacing = MaterialTheme.spacing

                    Box(modifier = Modifier
                        .constrainAs(refHeader) {
                            top.linkTo(parent.top, spacing.extraLarge)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            width = Dimension.fillToConstraints
                        }
                        .wrapContentSize()) {
                        AuthHeader()
                    }

                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { stringResource(R.string.name) },
                        modifier = Modifier.constrainAs(refName) {
                            top.linkTo(refHeader.bottom, spacing.extraLarge)
                            start.linkTo(parent.start, spacing.large)
                            end.linkTo(parent.end, spacing.large)
                            width = Dimension.fillToConstraints
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.None,
                            autoCorrect = false,
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = { focusManager.moveFocus(FocusDirection.Down) }
                        )
                    )

                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text(stringResource(R.string.email)) },
                        modifier = Modifier.constrainAs(refEmail) {
                            top.linkTo(refName.bottom, spacing.medium)
                            start.linkTo(parent.start, spacing.large)
                            end.linkTo(parent.end, spacing.large)
                            width = Dimension.fillToConstraints
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.None,
                            autoCorrect = false,
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next,
                        ),
                        keyboardActions = KeyboardActions(onNext = {
                            focusManager.moveFocus(
                                FocusDirection.Down
                            )
                        })
                    )

                    OutlinedTextField(
                        value = password,
                        onValueChange = {
                            if (it.length >= 8) password = it
                            else Toast.makeText(
                                context,
                                "Password should be 8 or more digits long ",
                                Toast.LENGTH_SHORT
                            ).show()
                        },
                        label = { Text(stringResource(R.string.password)) },
                        modifier = Modifier.constrainAs(refPassword) {
                            top.linkTo(refEmail.bottom, spacing.medium)
                            start.linkTo(parent.start, spacing.large)
                            end.linkTo(parent.end, spacing.large)
                            width = Dimension.fillToConstraints
                        },
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.None,
                            autoCorrect = false,
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(onDone = { callBack() })
                    )

                    Button(
                        onClick = { viewModel?.register(name, email, password) },
                        modifier = Modifier.constrainAs(refButtonSignUp) {
                            top.linkTo(refPassword.bottom, spacing.large)
                            start.linkTo(parent.start, spacing.extraLarge)
                            end.linkTo(parent.end, spacing.extraLarge)
                        }
                    ) {
                        Text(
                            stringResource(R.string.signup),
                            style = MaterialTheme.typography.titleMedium
                        )
                    }

                    Text(
                        modifier = Modifier
                            .padding(bottom = 30.dp)
                            .constrainAs(refTextSignUp) {
                                top.linkTo(refButtonSignUp.bottom, spacing.medium)
                                start.linkTo(parent.start, spacing.extraLarge)
                                end.linkTo(parent.end, spacing.extraLarge)
                            }
                            .clickable {
                                navController.navigate(ROUTE_LOGIN) {
                                    popUpTo(ROUTE_SIGNUP) { inclusive = true }
                                }
                            },
                        text = stringResource(R.string.already_have_account),
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    signUpFlow?.value?.let {
                        when (it) {
                            is Resource.Failure -> {
                                Toast.makeText(context, it.exception.message, Toast.LENGTH_SHORT)
                                    .show()
                            }

                            Resource.Loading -> {
                                CircularProgressIndicator(modifier = Modifier.constrainAs(refLoader) {
                                    top.linkTo(parent.top)
                                    bottom.linkTo(parent.bottom)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                })
                            }

                            is Resource.Success -> {
                                LaunchedEffect(Unit) {
                                    navController.navigate(ROUTE_HOME) {
                                        popUpTo(ROUTE_SIGNUP) { inclusive = true }
                                    }
                                }
                            }
                        } // when
                    } // signupFlow
                } // constraintLayout
            } // item
        } // LazyColumn
    } // Scaffold
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO)
@Composable
fun SignupScreenPreviewLight() {
    SignUpScreen(rememberNavController(), null)
}
