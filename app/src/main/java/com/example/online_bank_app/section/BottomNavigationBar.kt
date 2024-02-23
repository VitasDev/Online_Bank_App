package com.example.online_bank_app.section

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.online_bank_app.R
import com.example.online_bank_app.model.BottomNavigation
import com.example.online_bank_app.ui.theme.BlueBottomBar
import com.example.online_bank_app.ui.theme.GrayBottomBar
import com.example.online_bank_app.ui.theme.sfProText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

val itemsBottomNavigation = listOf(
    BottomNavigation(
        title = "Home",
        icon = R.drawable.ic_home
    ),

    BottomNavigation(
        title = "Transactions",
        icon = R.drawable.ic_transactions
    ),

    BottomNavigation(
        title = "My Cards",
        icon = R.drawable.ic_credit_card
    ),

    BottomNavigation(
        title = "Account",
        icon = R.drawable.ic_account
    )
)


@Preview
@Composable
fun BottomNavigationBar() {
    var indexSelected by remember {
        mutableIntStateOf(0)
    }

    NavigationBar(
        modifier = Modifier
            .height(76.dp)
    ) {
        Row(
            modifier = Modifier.background(Color.White)
        ) {
            itemsBottomNavigation.forEachIndexed { index, item ->
                NavigationBarItem(
                    interactionSource = NoRippleInteractionSource(),
                    colors = NavigationBarItemDefaults
                        .colors(
                            indicatorColor = Color.White
                        ),
                    selected = indexSelected == index,
                    onClick = {
                        indexSelected = index
                    },
                    icon = {
                        Icon(
                            modifier = Modifier
                                .size(24.dp),
                            painter = painterResource(id = item.icon),
                            contentDescription = null,
                            tint = if (indexSelected == index) {
                                BlueBottomBar
                            } else {
                                GrayBottomBar
                            }
                        )
                    },
                    label = {
                        Text(
                            text = item.title,
                            maxLines = 1,
                            fontSize = 10.sp,
                            color = if (indexSelected == index) {
                                BlueBottomBar
                            } else {
                                GrayBottomBar
                            },
                            fontFamily = sfProText,
                            fontWeight = FontWeight.Normal
                        )
                    }
                )
            }
        }
    }
}

class NoRippleInteractionSource : MutableInteractionSource {
    override val interactions: Flow<Interaction> = emptyFlow()
    override suspend fun emit(interaction: Interaction) {}
    override fun tryEmit(interaction: Interaction) = true
}