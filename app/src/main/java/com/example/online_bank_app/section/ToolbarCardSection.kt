package com.example.online_bank_app.section

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.online_bank_app.R
import com.example.online_bank_app.ui.theme.GrayArrow
import com.example.online_bank_app.ui.theme.ToolbarBlack
import com.example.online_bank_app.ui.theme.inter
import com.example.online_bank_app.viewmodel.MainViewModel

@Composable
fun ToolbarCardSection(viewModel: MainViewModel, navHostController: NavHostController) {

    val dataCard = viewModel.dataCard.value

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            modifier = Modifier
                .clickable {
                    navHostController.navigateUp()
                },
            painter = painterResource(id = R.drawable.ic_arrow_left),
            contentDescription = null,
            tint = ToolbarBlack
        )

        Row(
            modifier = Modifier
                .fillMaxWidth().padding(start = 50.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberImagePainter(data = dataCard!!.cardHolder.logoUrl),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .scale(1.2f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                modifier = Modifier
                    .width(58.dp),
                text = dataCard.cardName,
                fontSize = 16.sp,
                fontFamily = inter,
                fontWeight = FontWeight.Medium,
                color = ToolbarBlack,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.width(14.dp))

            Text(
                text = "•• ",
                fontSize = 12.sp,
                fontFamily = inter,
                fontWeight = FontWeight.Medium,
                color = GrayArrow
            )
            Text(
                text = dataCard.cardLast4.toString(),
                fontSize = 12.sp,
                fontFamily = inter,
                fontWeight = FontWeight.Medium,
                color = GrayArrow
            )
        }
    }
}