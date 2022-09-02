package kr.hs.dgsw.smartschool.data.network.api

import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.domain.model.meal.Meal
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {

    @GET("meal")
    suspend fun getMeal(
        @Query("year") year: Int,
        @Query("month") month: Int,
        @Query("day") day: Int
    ): Response<Meal>

    @GET("meal/calorie")
    suspend fun getCalorieOfMeal(): Response<String>
}
