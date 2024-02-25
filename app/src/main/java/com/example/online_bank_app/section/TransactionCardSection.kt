package com.example.online_bank_app.section

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.online_bank_app.R
import com.example.online_bank_app.api.CardTransactionApiState
import com.example.online_bank_app.model.Transaction
import com.example.online_bank_app.ui.theme.GrayArrow
import com.example.online_bank_app.ui.theme.GreenAmount
import com.example.online_bank_app.ui.theme.RedWarning
import com.example.online_bank_app.ui.theme.ToolbarBlack
import com.example.online_bank_app.ui.theme.inter
import com.example.online_bank_app.viewmodel.MainViewModel

@Composable
fun TransactionCardSection(viewModel: MainViewModel) {
    Card(
        modifier = Modifier
            .fillMaxSize(),
        colors = CardDefaults.outlinedCardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {
        Column(
            modifier = Modifier
                .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 4.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Activity",
                    fontSize = 16.sp,
                    fontFamily = inter,
                    fontWeight = FontWeight.SemiBold,
                    color = ToolbarBlack,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            when (val result = viewModel.responseCardTransaction.value) {
                is CardTransactionApiState.Success -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth(),
                    ) {
                        items(result.data) { transaction ->
                            TransactionCardItem(transaction)
                        }
                    }
                }

                is CardTransactionApiState.Failure -> {
                    Column(
                        modifier = Modifier
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

                CardTransactionApiState.Loading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                CardTransactionApiState.Empty -> {}
            }
        }
    }
}

@Composable
fun TransactionCardItem(
    transaction: Transaction
) {

    val imageStatusPayment = if (transaction.type == "Transfer") {
        painterResource(id = R.drawable.ic_status_payment_failed)
    } else {
        painterResource(id = R.drawable.ic_status_payment_success)
    }

    val transactionName = transaction.merchant.name
        ?: if (transaction.card != null) {
            transaction.card.cardName
        } else {
            transaction.type
        }

    val modifierDefaultImage = Modifier.size(40.dp)
    val modifierServerImage = Modifier
        .size(40.dp)
        .clip(CircleShape)
        .scale(1.2f)

    val modifierProfile =
        if (transaction.card != null) {
            modifierServerImage
        } else {
            modifierDefaultImage
        }

    val transformedAmount = convertNumberToRequiredType(transaction.amount).toString()

    val amount = if (transformedAmount.toFloat() > 0.0)
        "$$transformedAmount"
    else
        transformedAmount.replace("-", "-$")

    val amountColor = if (transaction.amount > 0.0) {
        GreenAmount
    } else {
        if (transaction.type == "Withdraw") {
            GrayArrow
        } else {
            ToolbarBlack
        }
    }

    Row(
        modifier = Modifier
            .height(64.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(modifier = Modifier.size(41.dp)) {
            Image(
                painter = painterResource(id = R.drawable.ic_round_card),
                contentDescription = null,
                modifier = modifierProfile
            )

            if (transaction.type == "Withdraw") {
                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .clip(CircleShape)
                        .background(RedWarning)
                        .align(Alignment.BottomEnd)
                )
            }
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Text(
                text = transactionName,
                fontSize = 16.sp,
                fontFamily = inter,
                fontWeight = FontWeight.Medium,
                color = ToolbarBlack,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false
                    )
                )
            )

            if (transaction.type == "Withdraw") {
                Text(
                    modifier = Modifier
                        .padding(top = 3.dp),
                    text = "Declined",
                    fontSize = 14.sp,
                    fontFamily = inter,
                    fontWeight = FontWeight.Medium,
                    color = RedWarning,
                    style = TextStyle(
                        platformStyle = PlatformTextStyle(
                            includeFontPadding = false
                        )
                    )
                )
            }
        }

        Text(
            modifier = Modifier
                .weight(1f),
            text = amount,
            fontSize = 16.sp,
            fontFamily = inter,
            fontWeight = FontWeight.Medium,
            color = amountColor,
            textAlign = TextAlign.End,
            style = if (transaction.type == "Withdraw") TextStyle(textDecoration = TextDecoration.LineThrough) else TextStyle()
        )

        Spacer(modifier = Modifier.width(8.dp))

        Box(modifier = Modifier.size(24.dp)) {
            if (transaction.type != "Withdraw") {
                Icon(
                    painter = imageStatusPayment,
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            }
        }
    }
}