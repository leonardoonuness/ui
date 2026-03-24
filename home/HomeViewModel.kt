package ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ui.data.model.BookingRequest
import ui.data.repository.ServiceRepository

class HomeViewModel(
    private val repository: ServiceRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState(isLoading = true))
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        refreshHome()
    }

    fun refreshHome() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            runCatching {
                val categories = repository.getCategories()
                val recommended = repository.getRecommendedServices()
                categories to recommended
            }.onSuccess { (categories, recommended) ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        categories = categories,
                        recommendedServices = recommended
                    )
                }
            }.onFailure { error ->
                _uiState.update {
                    it.copy(isLoading = false, errorMessage = error.message ?: "Erro ao carregar dados")
                }
            }
        }
    }

    fun onQueryChange(query: String) {
        _uiState.update { it.copy(query = query) }
    }

    fun onCategorySelected(categoryId: String?) {
        _uiState.update { it.copy(selectedCategoryId = categoryId) }
    }

    fun onBookService(serviceId: String) {
        viewModelScope.launch {
            val request = BookingRequest(
                serviceId = serviceId,
                scheduledAtIso = "2026-03-24T10:00:00Z"
            )
            runCatching { repository.createBooking(request) }
                .onFailure { error ->
                    _uiState.update { it.copy(errorMessage = error.message ?: "Falha ao reservar") }
                }
        }
    }

    companion object {
        fun factory(repository: ServiceRepository): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return HomeViewModel(repository) as T
                }
            }
    }
}
