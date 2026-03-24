package ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ui.components.CategoryItem
import ui.components.PromoBanner
import ui.components.SearchBar
import ui.components.ServiceCard
import ui.data.remote.NetworkProvider
import ui.data.repository.ServiceRepositoryImpl
import ui.home.HomeUiState
import ui.home.HomeViewModel

@Composable
fun HomeScreenRoute() {
    val repository = ServiceRepositoryImpl(NetworkProvider.apiService)
    val viewModel: HomeViewModel = viewModel(factory = HomeViewModel.factory(repository))
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreen(
        state = state,
        onQueryChange = viewModel::onQueryChange,
        onCategorySelected = viewModel::onCategorySelected,
        onReserveClick = viewModel::onBookService,
        onRetry = viewModel::refreshHome
    )
}

@Composable
fun HomeScreen(
    state: HomeUiState,
    onQueryChange: (String) -> Unit,
    onCategorySelected: (String?) -> Unit,
    onReserveClick: (String) -> Unit,
    onRetry: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        SearchBar(text = state.query, onTextChange = onQueryChange)
        Spacer(Modifier.height(16.dp))
        PromoBanner()
        Spacer(Modifier.height(24.dp))

        if (state.errorMessage != null) {
            AssistChip(onClick = onRetry, label = { Text("Tentar novamente") })
            Spacer(Modifier.height(12.dp))
        }

        Text("Categorias", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(12.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            state.categories.take(4).forEach { category ->
                CategoryItem(
                    title = category.name,
                    icon = {},
                    bg = MaterialTheme.colorScheme.primary.copy(0.1f)
                )
            }
        }

        Spacer(Modifier.height(24.dp))
        Text("Recomendados para você", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(12.dp))

        when {
            state.isLoading -> {
                CircularProgressIndicator()
            }

            state.recommendedServices.isEmpty() -> {
                Text("Nenhum serviço encontrado")
            }

            else -> {
                state.recommendedServices.forEachIndexed { index, service ->
                    ServiceCard(
                        name = service.providerName,
                        description = "${service.title} • ${service.distanceKm}km",
                        price = "R$ ${service.startingPrice}",
                        rating = service.rating.toString(),
                        imageRes = android.R.drawable.sym_def_app_icon,
                        onReserveClick = { onReserveClick(service.id) }
                    )
                    if (index < state.recommendedServices.lastIndex) {
                        Spacer(Modifier.height(12.dp))
                    }
                }
            }
        }
    }
}
