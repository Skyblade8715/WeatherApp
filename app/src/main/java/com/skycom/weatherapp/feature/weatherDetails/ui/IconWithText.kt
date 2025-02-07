package com.skycom.weatherapp.feature.weatherDetails.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@Composable
fun IconWithText(
    contentDescription: String,
    modifier: Modifier = Modifier,
    text: String? = null,
    annotatedText: AnnotatedString? = null,
    resId: Int? = null,
    imageVector: ImageVector? = null,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (resId != null) {
            Icon(
                tint = MaterialTheme.colorScheme.surfaceTint,
                modifier = Modifier.size(34.dp),
                painter = painterResource(id = resId),
                contentDescription = contentDescription,
            )
        } else if (imageVector != null) {
            Icon(
                tint = MaterialTheme.colorScheme.surfaceTint,
                modifier = Modifier.size(34.dp),
                imageVector = imageVector,
                contentDescription = contentDescription,
            )
        }
        if (text != null) {
            Text(
                modifier = Modifier.weight(1f),
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                text = text,
                textAlign = TextAlign.End,
            )
        }
        if (annotatedText != null) {
            Text(
                modifier = Modifier.weight(1f),
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                text = annotatedText,
                textAlign = TextAlign.End,
            )
        }
    }
}
