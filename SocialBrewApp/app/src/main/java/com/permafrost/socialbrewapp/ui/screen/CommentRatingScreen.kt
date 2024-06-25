package com.permafrost.socialbrewapp.ui.screen

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.permafrost.socialbrewapp.R
import com.permafrost.socialbrewapp.data.api.CommentarySchemaWithObjectUser
import com.permafrost.socialbrewapp.data.api.NewCommentRequest
import com.permafrost.socialbrewapp.ui.component.BottomNavBar
import com.permafrost.socialbrewapp.ui.component.TopBar
import com.permafrost.socialbrewapp.ui.theme.Black
import com.permafrost.socialbrewapp.ui.theme.White
import com.permafrost.socialbrewapp.ui.viewmodel.CommentViewModel
import com.permafrost.socialbrewapp.ui.viewmodel.ProductDetailViewModel


@Composable
fun CommentRatingScreen(
    productId: String,
    navController: NavHostController,
    commentViewModel: CommentViewModel = viewModel(),
    productDetailViewModel: ProductDetailViewModel = viewModel()
) {
    val product by productDetailViewModel.product.collectAsState()
    var commentText by remember { mutableStateOf("") }
    var rating by remember { mutableIntStateOf(0) }

    LaunchedEffect(productId) {
        productDetailViewModel.loadProduct(productId)
    }

    Scaffold(
        topBar = { TopBar(title = product?.name ?: "BeerLab") },
        bottomBar = { BottomNavBar(navController = navController) }
    ) { innerPadding ->
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = colorResource(id = R.color.background)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                item {
                    product?.let { product ->
                        Image(
                            painter = rememberAsyncImagePainter(product.image),
                            contentDescription = "Beer",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .padding(16.dp)
                        )

                        Row(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Box(
                                modifier = Modifier.padding(8.dp),
                                contentAlignment = Alignment.CenterEnd
                            ) {
                                Column {
                                    Text(
                                        text = product.name,
                                        color = White,
                                        fontSize = 32.sp,
                                        fontFamily = FontFamily.Default,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = "$${product.price}",
                                        color = White,
                                        fontSize = 32.sp,
                                        fontFamily = FontFamily.Default,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }

                        Row(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            Rating(rating = rating) { newRating ->
                                rating = newRating
                            }
                        }

                        TextField(
                            value = commentText,
                            onValueChange = { commentText = it },
                            label = { Text("Escribe tu reseña") },
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color.White,
                                unfocusedBorderColor = Color.Gray,
                                unfocusedTextColor = Color.White,
                                unfocusedLabelColor = Color.White,
                                focusedTextColor = Color.White,
                                focusedLabelColor = Color.White,
                            )
                        )

                        Button(
                            onClick = {
                                val newComment =
                                    NewCommentRequest(text = commentText, points = rating.toDouble())
                                commentViewModel.addComment(product.id, newComment)



                            },
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth()
                        ) {
                            Text("Enviar Reseña")
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
                    }
                }

                items(product?.comments ?: emptyList()) { comment ->
                    CommentCard(comment)
                }
            }
        }
    }
}

@Composable
fun Rating(rating: Int, onRatingChange: (Int) -> Unit) {
    Row {
        repeat(rating) {
            Icon(
                modifier = Modifier
                    .size(40.dp)
                    .clickable { onRatingChange(it + 1) },
                imageVector = Icons.Filled.Star,
                contentDescription = "Star",
                tint = Color.Red
            )
        }
        repeat(5 - rating) {
            Icon(
                modifier = Modifier
                    .size(40.dp)
                    .clickable { onRatingChange(rating + it + 1) },
                imageVector = Icons.Filled.Star,
                contentDescription = "Star",
                tint = Color.Gray
            )
        }
    }
}

@Composable
fun CommentCard(comment: CommentarySchemaWithObjectUser) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(Black),
        border = BorderStroke(1.dp, White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = comment.user.username,
                modifier = Modifier
                    .padding(0.dp, 8.dp),
                textAlign = TextAlign.Start,
                color = White,
                fontSize = 16.sp,
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = comment.text,
                color = White,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(0.dp, 0.dp, 32.dp, 0.dp),
                fontFamily = FontFamily.Default,
                letterSpacing = 2.sp
            )
            Rating(rating = comment.points.toInt()) { }
        }
    }
}


