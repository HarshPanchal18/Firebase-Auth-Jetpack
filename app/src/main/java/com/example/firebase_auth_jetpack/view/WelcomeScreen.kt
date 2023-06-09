package com.example.firebase_auth_jetpack.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import com.example.firebase_auth_jetpack.R
import com.example.firebase_auth_jetpack.component.AuthHeader
import com.example.firebase_auth_jetpack.navigation.ROUTE_LOGIN
import com.example.firebase_auth_jetpack.navigation.ROUTE_SIGNUP
import com.example.firebase_auth_jetpack.ui.theme.Purple200
import com.example.firebase_auth_jetpack.ui.theme.Purple40
import com.example.firebase_auth_jetpack.ui.theme.spacing

@Composable
fun WelcomeScreen(navController: NavController) {

    LazyColumn {
        item {
            ConstraintLayout(
                modifier = Modifier.fillMaxSize()
            ) {
                val (refHeader, refWelcomeBack, refButtonSignup, refButtonLogin) = createRefs()
                val spacing = MaterialTheme.spacing

                Box(modifier = Modifier
                    .constrainAs(refHeader) {
                        top.linkTo(parent.top, spacing.extraLarge)
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                        width = Dimension.fillToConstraints
                    }
                    .wrapContentSize()
                ) {
                    AuthHeader()
                }

                Text(
                    stringResource(R.string.welcome_back),
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    modifier = Modifier
                        .padding(bottom = 30.dp)
                        .constrainAs(refWelcomeBack) {
                            top.linkTo(refHeader.bottom, spacing.extraLarge)
                            start.linkTo(parent.start, spacing.large)
                            end.linkTo(parent.end, spacing.large)
                        }
                )

                Button(
                    onClick = {
                        navController.navigate(ROUTE_LOGIN)
                    },
                    colors = ButtonDefaults.outlinedButtonColors(containerColor = Purple40),
                    modifier = Modifier.constrainAs(refButtonLogin) {
                        top.linkTo(refWelcomeBack.bottom, spacing.large)
                        start.linkTo(parent.start, spacing.extraLarge)
                        end.linkTo(parent.end, spacing.extraLarge)
                        width = Dimension.fillToConstraints
                    }
                ) {
                    Text(
                        stringResource(R.string.login),
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White
                    )
                }

                Button(
                    onClick = {
                        navController.navigate(ROUTE_SIGNUP)
                    },
                    border = BorderStroke(1.dp, Color.White),
                    colors = ButtonDefaults.outlinedButtonColors(containerColor = Purple200),
                    modifier = Modifier.constrainAs(refButtonSignup) {
                        top.linkTo(refButtonLogin.bottom, spacing.large)
                        start.linkTo(parent.start, spacing.extraLarge)
                        end.linkTo(parent.end, spacing.extraLarge)
                        width = Dimension.fillToConstraints
                    }
                ) {
                    Text(
                        stringResource(R.string.signup),
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White
                    )
                }
            } // ConstraintLayout
        } // item
    } // LazyColumn
}
