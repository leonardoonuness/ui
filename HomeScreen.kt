package ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp
import ui.components.*

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        SearchBar()
        Spacer(Modifier.height(16.dp))
        PromoBanner()
        Spacer(Modifier.height(24.dp))

        Text("Categorias", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(12.dp))

        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            CategoryItem("Limpeza", {}, MaterialTheme.colorScheme.primary.copy(0.1f))
            CategoryItem("Reformas", {}, MaterialTheme.colorScheme.secondary.copy(0.1f))
            CategoryItem("Aulas", {}, MaterialTheme.colorScheme.tertiary.copy(0.1f))
            CategoryItem("Eventos", {}, MaterialTheme.colorScheme.primary.copy(0.15f))
        }

        Spacer(Modifier.height(24.dp))
        Text("Recomendados para você", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(12.dp))

        ServiceCard(
            "Carlos Eletricista",
            "Eletricista Residencial • 1.2km",
            "R$ 80",
            "4.9",
            imageRes = android.R.drawable.sym_def_app_icon
        )

        Spacer(Modifier.height(12.dp))

        ServiceCard(
            "Mariana Limpeza Profunda",
            "Diarista e Organização • 3.5km",
            "R$ 150",
            "4.8",
            imageRes = android.R.drawable.sym_def_app_icon
        )
    }
}
