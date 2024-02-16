package com.littlelemon.leproject.compose.citycard

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

@Composable
fun AutoResizedText(
    text: String,
    style: TextStyle = MaterialTheme.typography.bodyMedium,
    modifier: Modifier = Modifier,
    color: Color = style.color,
    fontWeight: FontWeight,
) {
    var resizedTextStyle by remember {
        mutableStateOf(style)
    }
    var shouldDraw by remember {
        mutableStateOf(false)
    }

    Text(
        text = text,
        color = color,
        fontWeight = fontWeight,
        modifier = modifier.drawWithContent {
            if (shouldDraw){
                drawContent()
            }
        },
        softWrap = false,
        style = resizedTextStyle,
        onTextLayout = {result ->
            if(result.didOverflowWidth){
                resizedTextStyle = resizedTextStyle.copy(
                    fontSize = resizedTextStyle.fontSize*0.95
                )
            } else {
                shouldDraw = true
            }
        }
    )
}