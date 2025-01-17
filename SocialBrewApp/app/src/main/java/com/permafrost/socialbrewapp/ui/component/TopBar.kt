package com.permafrost.socialbrewapp.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.permafrost.socialbrewapp.R
import com.permafrost.socialbrewapp.ui.theme.fontFamily


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(title: String) {
    TopAppBar(
        title = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = title,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontFamily = fontFamily,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(color = colorResource(id = R.color.primary_red)),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorResource(id = R.color.primary_red)
        )
    )
}

