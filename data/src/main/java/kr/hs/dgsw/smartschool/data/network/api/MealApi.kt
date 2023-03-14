package kr.hs.dgsw.smartschool.data.network.api

import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.data.network.url.DodamUrl
import kr.hs.dgsw.smartschool.domain.model.meal.Calorie
import kr.hs.dgsw.smartschool.domain.model.meal.Meal
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {

    @GET(DodamUrl.MEAL)
    suspend fun getMeal(
        @Query("year") year: Int,
        @Query("month") month: Int,
        @Query("day") day: Int
    ): Response<Meal>

    @GET(DodamUrl.Meal.CALORIE)
    suspend fun getCalorieOfMeal(): Response<Calorie>

    @GET(DodamUrl.Meal.MONTH)
    suspend fun getMealOfMonth(
        @Query("month") month: Int,
        @Query("year") year: Int
    ): Response<List<Meal>>
}
