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
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.online_bank_app.R
import com.example.online_bank_app.api.TransactionApiState
import com.example.online_bank_app.model.Transaction
import com.example.online_bank_app.ui.theme.GrayArrow
import com.example.online_bank_app.ui.theme.GreenAmount
import com.example.online_bank_app.ui.theme.RedWarning
import com.example.online_bank_app.ui.theme.ToolbarBlack
import com.example.online_bank_app.ui.theme.inter
import com.example.online_bank_app.viewmodel.MainViewModel

@Composable
fun TransactionsSection(viewModel: MainViewModel) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp, end = 14.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Recent transactions",
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

        when (val result = viewModel.responseTransaction.value) {
            is TransactionApiState.Success -> {
                LazyColumn(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth(),
                ) {
                    items(result.data.transactions) { transaction ->
                        TransactionItem(transaction)
                    }
                }
            }

            is TransactionApiState.Failure -> {
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

            TransactionApiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            TransactionApiState.Empty -> {}
        }
    }
}


@OptIn(ExperimentalCoilApi::class)
@Composable
fun TransactionItem(
    transaction: Transaction
) {
    var imageProfile = painterResource(id = R.drawable.ic_arrow_down_left)
    if (transaction.card != null) {
        imageProfile = rememberImagePainter(data = transaction.card.cardHolder.logoUrl)
    }

    val imageStatusPayment = if (transaction.type == "Transfer") {
        painterResource(id = R.drawable.ic_status_payment_failed)
    } else {
        painterResource(id = R.drawable.ic_status_payment_success)
    }

    val transactionName = if (transaction.card != null) {
        transaction.card.cardName
    } else {
        transaction.merchant.name
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

    val cardLast4Number = if (transaction.card != null) {
        transaction.card.cardLast4
    } else {
        transaction.account.accountLast4
    }.toString()

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
            .padding(start = 16.dp, end = 16.dp)
            .height(64.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(modifier = Modifier.size(41.dp)) {
            Image(
                painter = imageProfile,
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
                text = transactionName!!,
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

            Row(
                modifier = Modifier.padding(top = 1.dp)
            ) {
                Text(
                    text = "•• ",
                    fontSize = 12.sp,
                    fontFamily = inter,
                    fontWeight = FontWeight.Medium,
                    color = GrayArrow,
                    style = TextStyle(
                        platformStyle = PlatformTextStyle(
                            includeFontPadding = false
                        )
                    )
                )
                Text(
                    text = cardLast4Number,
                    fontSize = 12.sp,
                    fontFamily = inter,
                    fontWeight = FontWeight.Medium,
                    color = GrayArrow,
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

        Spacer(modifier = Modifier.width(12.dp))

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

fun convertNumberToRequiredType(value: Float): Any {
    return if (value % 1 == 0f) {
        value.toInt()
    } else {
        value
    }
}