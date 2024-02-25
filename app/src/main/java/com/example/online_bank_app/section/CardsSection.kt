package com.example.online_bank_app.section

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.online_bank_app.R
import com.example.online_bank_app.api.CardApiState
import com.example.online_bank_app.model.Card
import com.example.online_bank_app.ui.theme.CardBorder
import com.example.online_bank_app.ui.theme.GrayArrow
import com.example.online_bank_app.ui.theme.RedWarning
import com.example.online_bank_app.ui.theme.ToolbarBlack
import com.example.online_bank_app.ui.theme.inter
import com.example.online_bank_app.viewmodel.MainViewModel

@Composable
fun CardsSection(viewModel: MainViewModel, navHostController: NavHostController) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .fillMaxWidth()
            .height(176.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp, end = 14.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "My cards",
                fontSize = 16.sp,
                fontFamily = inter,
                fontWeight = FontWeight.SemiBold,
                color = ToolbarBlack
            )
            Text(
                text = "See All",
                fontSize = 14.sp,
                fontFamily = inter,
                fontWeight = FontWeight.Medium,
                color = ToolbarBlack,
                textDecoration = TextDecoration.Underline
            )
        }


        when (val result = viewModel.responseCard.value) {
            is CardApiState.Success -> {
                LazyColumn(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth(),
                ) {
                    items(result.data.cards) { card ->
                        CardItem(card, navHostController, viewModel)
                    }
                }
            }

            is CardApiState.Failure -> {
                Column(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Box(
                        modifier = Modifier
                            .size(62.dp)
                            .clip(CircleShape)
                            .background(RedWarning),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(40.dp),
                            painter = painterResource(id = R.drawable.ic_warning),
                            contentDescription = null,
                            tint = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))


                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Some problems with server or your internet!",
                        fontSize = 14.sp,
                        fontFamily = inter,
                        fontWeight = FontWeight.Medium,
                        color = ToolbarBlack,
                        textAlign = TextAlign.Center
                    )
                }
            }

            CardApiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            CardApiState.Empty -> {}
        }
    }
}


@OptIn(ExperimentalCoilApi::class)
@Composable
fun CardItem(
    card: Card, navHostController: NavHostController, viewModel: MainViewModel
) {
    Row(
        modifier = Modifier
            .padding(start = 22.dp, end = 16.dp)
            .height(64.dp)
            .fillMaxWidth()
            .clickable {
                viewModel.setDataCard(card)
                viewModel.getCardTransactions(card.id)
                navHostController.navigate("Card_Page")
            },
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box {
            OutlinedCard(
                modifier = Modifier
                    .size(48.dp, 32.dp),
                border = BorderStroke(1.dp, CardBorder),
                shape = RoundedCornerShape(4.dp),
                colors = CardDefaults.outlinedCardColors(
                    contentColor = Color.White,
                    containerColor = ToolbarBlack
                )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    Text(
                        modifier = Modifier.padding(bottom = 6.dp, end = 6.dp),
                        text = card.cardLast4.toString(),
                        fontSize = 10.sp,
                        fontFamily = inter,
                        fontWeight = FontWeight.SemiBold,
                        style = TextStyle(
                            platformStyle = PlatformTextStyle(
                                includeFontPadding = false
                            )
                        )
                    )
                }
            }

            Image(
                painter = rememberImagePainter(data = card.cardHolder.logoUrl),
                contentDescription = null,
                modifier = Modifier
                    .offset(y = (-11).dp, x = (-11).dp)
                    .size(28.dp)
                    .clip(CircleShape)
                    .scale(1.2f)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            modifier = Modifier
                .weight(1f),
            text = card.cardName,
            fontSize = 16.sp,
            fontFamily = inter,
            fontWeight = FontWeight.Medium,
            color = ToolbarBlack
        )

        Spacer(modifier = Modifier.width(12.dp))

        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_to_right),
            contentDescription = null,
            tint = GrayArrow
        )
    }
}