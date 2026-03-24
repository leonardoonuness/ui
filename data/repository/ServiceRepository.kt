package ui.data.repository

import ui.data.model.BookingRequest
import ui.data.model.BookingResult
import ui.data.model.Category
import ui.data.model.Service
import ui.data.remote.ApiService

interface ServiceRepository {
    suspend fun getCategories(): List<Category>
    suspend fun getRecommendedServices(): List<Service>
    suspend fun searchServices(query: String, categoryId: String? = null): List<Service>
    suspend fun createBooking(request: BookingRequest): BookingResult
}

class ServiceRepositoryImpl(
    private val apiService: ApiService
) : ServiceRepository {

    override suspend fun getCategories(): List<Category> = apiService.getCategories()

    override suspend fun getRecommendedServices(): List<Service> = apiService.getRecommendedServices()

    override suspend fun searchServices(query: String, categoryId: String?): List<Service> {
        return apiService.getServices(query = query, categoryId = categoryId)
    }

    override suspend fun createBooking(request: BookingRequest): BookingResult {
        return apiService.createBooking(request)
    }
}
