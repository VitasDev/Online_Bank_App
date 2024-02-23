package com.example.online_bank_app.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.Font
import com.example.online_bank_app.R

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)

val sfProText = FontFamily(
    listOf(
        Font(R.font.sfprotext_light, FontWeight.Light),
        Font(R.font.sfprotext_regular, FontWeight.Normal),
        Font(R.font.sfprotext_medium, FontWeight.Medium),
        Font(R.font.sfprotext_semibold, FontWeight.SemiBold),
        Font(R.font.sfprotext_bold, FontWeight.Bold)
    )
)

val inter = FontFamily(
    listOf(
        Font(R.font.inter_regular, FontWeight.Normal),
        Font(R.font.inter_medium, FontWeight.Medium),
        Font(R.font.inter_semibold, FontWeight.SemiBold),
        Font(R.font.inter_extrabold, FontWeight.ExtraBold)
    )
)