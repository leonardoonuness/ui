package ui.data.remote

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import ui.data.model.BookingRequest
import ui.data.model.BookingResult
import ui.data.model.Category
import ui.data.model.Service

interface ApiService {

    @GET("categories")
    suspend fun getCategories(): List<Category>

    @GET("services/recommended")
    suspend fun getRecommendedServices(): List<Service>

    @GET("services")
    suspend fun getServices(
        @Query("query") query: String? = null,
        @Query("categoryId") categoryId: String? = null,
        @Query("page") page: Int? = null
    ): List<Service>

    @POST("bookings")
    suspend fun createBooking(@Body request: BookingRequest): BookingResult
}
