package com.example.online_bank_app.section

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.online_bank_app.R
import com.example.online_bank_app.ui.theme.Divider
import com.example.online_bank_app.ui.theme.ToolbarBlack
import com.example.online_bank_app.ui.theme.inter

@Preview
@Composable
fun ToolbarMainSection() {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Money",
                fontSize = 32.sp,
                fontFamily = inter,
                fontWeight = FontWeight.ExtraBold,
                color = ToolbarBlack
            )

            Row {
                Box(
                    modifier = Modifier
                        .clickable { }
                        .size(44.dp)
                        .padding(10.dp),
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_add),
                        contentDescription = null,
                        tint = ToolbarBlack
                    )
                }

                Box(
                    modifier = Modifier
                        .clickable { }
                        .size(44.dp)
                        .padding(10.dp),
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_bank),
                        contentDescription = null,
                        tint = ToolbarBlack
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = Divider
        )
    }
}