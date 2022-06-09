package kr.hs.dgsw.smartschool.data.network.api

import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.data.network.response.data.MealData
import kr.hs.dgsw.smartschool.domain.model.meal.Meal
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {
    @GET("meal")
    suspend fun getAllMeals(
        @Query("year") year: Int,
        @Query("month") month: Int
    ): Response<List<Meal>>
}