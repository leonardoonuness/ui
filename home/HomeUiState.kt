package ui.home

import ui.data.model.Category
import ui.data.model.Service

data class HomeUiState(
    val isLoading: Boolean = false,
    val query: String = "",
    val categories: List<Category> = emptyList(),
    val recommendedServices: List<Service> = emptyList(),
    val selectedCategoryId: String? = null,
    val errorMessage: String? = null
)
