package ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import ui.theme.BlueLight
import ui.theme.BluePrimary

@Composable
fun PromoBanner() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .background(
                Brush.horizontalGradient(listOf(BluePrimary, BlueLight)),
                RoundedCornerShape(20.dp)
            )
            .padding(20.dp)
    ) {
        Column {
            Text("Ganhe 15% OFF", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.onPrimary)
            Spacer(Modifier.height(4.dp))
            Text("Na sua primeira limpeza residencial", color = MaterialTheme.colorScheme.onPrimary)
            Spacer(Modifier.height(12.dp))
            AssistChip(
                onClick = {},
                label = { Text("Cupom: BEMVINDO") }
            )
        }
    }
}
