package com.permafrost.socialbrewapp.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.permafrost.socialbrewapp.R
import com.permafrost.socialbrewapp.ui.component.TopBar
import com.permafrost.socialbrewapp.ui.theme.Black
import com.permafrost.socialbrewapp.ui.theme.White
@Composable
fun CommentRatingScreen() {

    Scaffold(
        topBar = {
            TopBar(title = "BeerLab")
        },
      /*  bottomBar = {
            BottomNavBar(navController = navController)
        },*/
    ){ innerPadding ->
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = colorResource(id = R.color.background)
        ){

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.beer),
                contentDescription = "Beer",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(16.dp)
            )

            Row (modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween){
                Box(
                    modifier = Modifier.padding(8.dp),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Column {
                        Text(
                            text = "Cerveza 330ml",
                            color = White,
                            fontSize = 32.sp,
                            fontFamily = FontFamily.Default,
                            fontWeight = FontWeight.Bold
                            
                        )
                        Text(
                            text = "$1.80",
                            color = White,
                            fontSize = 32.sp,
                            fontFamily = FontFamily.Default,
                            fontWeight = FontWeight.Bold
                        )
                    }

                }

                IconButton(onClick = { /* Handle favorite click */ }) {
                    Icon(
                        modifier = Modifier.size(40.dp),
                        imageVector = Icons.Outlined.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = White
                    )
                }
            }

            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Rating(rating = 4)

            }
            Text(
                text = "Reseñas:",
                modifier = Modifier.padding(16.dp, 0.dp),
                color = White,
                fontSize = 25.sp,
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start
            )
            CommentCards()

        }

        }
    }

}

@Composable
fun Rating(rating: Int) {
    Row {
        repeat(rating) {
            Icon(
                modifier = Modifier.size(40.dp),
                imageVector = Icons.Filled.Star,
                contentDescription = "Star",
                tint = Color.Red
            )
        }
        repeat(5 - rating) {
            Icon(
                modifier = Modifier.size(40.dp),
                imageVector = Icons.Filled.Star,
                contentDescription = "Star",
                tint = Color.Gray
            )
        }
    }
}

@Composable
fun CommentCards() {
    Card(
        modifier = Modifier
            .padding(26.dp, 8.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(Black),
        border = BorderStroke(1.dp, White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Oscar Ayala",
                modifier = Modifier
                    .padding(0.dp, 8.dp),
                textAlign = TextAlign.Start,
                color = White,
                fontSize = 16.sp,
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Bold
            )
            Text(text = "Siempre disponibles heladas en Beerlab y por este precio es el mejor lugar para echarse una!",
                color = White,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(0.dp, 0.dp, 32.dp, 0.dp),
                fontFamily = FontFamily.Default,
                letterSpacing = 2.sp
                )
        }
    }
}


@Preview
@Composable
private fun CommentRatingPreview() {
    CommentRatingScreen()
}