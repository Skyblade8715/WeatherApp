package com.skycom.weatherapp.core.common.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import com.skycom.weatherapp.R


@Composable
fun Double.annotateWithTemperatureColor(): AnnotatedString {
    val color = when {
        this < 10      -> Color.Blue
        this in 10.0..20.0 -> Color.Black
        else           -> Color.Red
    }
    return AnnotatedString(
        text = stringResource(
            R.string.degrees_celcius,
            this
        ),
        spanStyle = SpanStyle(
            color = color
        )
    )
}

fun Double.convertToDMS(isLatitude: Boolean): String {
    val degrees = this.toInt()
    val minutesDecimal = (this - degrees) * 60
    val minutes = minutesDecimal.toInt()
    val seconds = ((minutesDecimal - minutes) * 60).toInt()

    val direction = when {
        isLatitude && this >= 0 -> "N"
        isLatitude && this < 0 -> "S"
        !isLatitude && this >= 0 -> "E"
        else -> "W"
    }

    return "$degrees°$minutes'$seconds\" $direction"
}