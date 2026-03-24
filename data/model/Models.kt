package ui.data.model

data class AuthTokens(
    val accessToken: String,
    val refreshToken: String? = null
)

data class UserSession(
    val userId: String,
    val userName: String,
    val tokens: AuthTokens
)

data class Category(
    val id: String,
    val name: String,
    val icon: String? = null
)

data class Service(
    val id: String,
    val providerName: String,
    val title: String,
    val distanceKm: Double,
    val startingPrice: Double,
    val rating: Double,
    val imageUrl: String? = null,
    val categoryId: String? = null
)

data class BookingRequest(
    val serviceId: String,
    val scheduledAtIso: String,
    val notes: String? = null
)

data class BookingResult(
    val bookingId: String,
    val status: String,
    val message: String? = null
)
