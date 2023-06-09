package com.example.firebase_auth_jetpack.view

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
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
fun LoginScreen(
    navController: NavController,
    viewModel: AuthViewModel?
) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val kc = LocalSoftwareKeyboardController.current
    val callBack = { kc?.hide() }
    val focusManager = LocalFocusManager.current

    val loginFlow = viewModel?.loginFlow?.collectAsState()
    LazyColumn {
        item {
            ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                val (refHeader, refEmail, refPassword, refButtonLogin, refTextSignUp, refLoader) = createRefs()
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
                    value = email,
                    onValueChange = { email = it },
                    label = { Text(stringResource(R.string.email)) },
                    modifier = Modifier.constrainAs(refEmail) {
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
                    value = password,
                    onValueChange = { password = it },
                    label = { Text(stringResource(R.string.password)) },
                    modifier = Modifier.constrainAs(refPassword) {
                        top.linkTo(refEmail.bottom, spacing.medium)
                        start.linkTo(parent.start, spacing.large)
                        end.linkTo(parent.end, spacing.large)
                        width = Dimension.fillToConstraints
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.None,
                        autoCorrect = false,
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { callBack() }
                    )
                )

                Button(
                    onClick = { viewModel?.login(email, password) },
                    modifier = Modifier.constrainAs(refButtonLogin) {
                        top.linkTo(refPassword.bottom, spacing.large)
                        start.linkTo(parent.start, spacing.extraLarge)
                        end.linkTo(parent.end, spacing.extraLarge)
                        width = Dimension.fillToConstraints
                    }
                ) {
                    Text(
                        stringResource(id = R.string.login),
                        style = MaterialTheme.typography.titleMedium
                    )
                }

                Text(
                    stringResource(id = R.string.dont_have_account),
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .padding(bottom = 30.dp)
                        .constrainAs(refTextSignUp) {
                            top.linkTo(refButtonLogin.bottom, spacing.medium)
                            start.linkTo(parent.start, spacing.extraLarge)
                            end.linkTo(parent.end, spacing.extraLarge)
                        }
                        .clickable {
                            navController.navigate(ROUTE_SIGNUP) {
                                popUpTo(ROUTE_LOGIN) { inclusive = true }
                            }
                        }
                )

                loginFlow?.value?.let {
                    when (it) {
                        is Resource.Success -> {
                            LaunchedEffect(Unit) {
                                navController.navigate(ROUTE_HOME) {
                                    popUpTo(ROUTE_LOGIN) { inclusive = true }
                                }
                            }
                        }

                        Resource.Loading -> {
                            CircularProgressIndicator(
                                modifier = Modifier.constrainAs(refLoader) {
                                    top.linkTo(parent.top)
                                    bottom.linkTo(parent.bottom)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                })
                        }

                        is Resource.Failure -> {
                            LaunchedEffect(Unit) {
                                navController.navigate(ROUTE_HOME) {
                                    popUpTo(ROUTE_LOGIN) { inclusive = true }
                                }
                            }
                        }
                    } // when
                } // loginFlow
            } // ConstraintLayout
        } // item
    } // LazyColumn
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO)
@Composable
fun LoginScreenPreviewLight() {
    LoginScreen(rememberNavController(), null)
}
