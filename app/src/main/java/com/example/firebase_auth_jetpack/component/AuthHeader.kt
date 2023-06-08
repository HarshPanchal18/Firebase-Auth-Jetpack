package com.example.firebase_auth_jetpack.component

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.firebase_auth_jetpack.R
import com.example.firebase_auth_jetpack.ui.theme.*

@Composable
fun AuthHeader() {
    Column(
        modifier = Modifier.wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val spacing = MaterialTheme.spacing

        Image(
            painter = painterResource(id = R.drawable.ic_app_logo),
            contentDescription = "",
            modifier = Modifier.size(128.dp,128.dp)
        )

        Text(text = stringResource(id = R.string.app_name),
            modifier = Modifier.fillMaxWidth()
                .wrapContentHeight()
                .padding(top = spacing.medium),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun AppHeaderLight() {
    AuthHeader()
}
