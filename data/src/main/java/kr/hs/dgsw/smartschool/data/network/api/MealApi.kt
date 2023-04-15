package kr.hs.dgsw.smartschool.data.network.api

import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.data.network.response.meal.MealResponse
import kr.hs.dgsw.smartschool.data.network.url.DodamUrl
import kr.hs.dgsw.smartschool.domain.model.meal.Calorie
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {

    @GET(DodamUrl.MEAL)
    suspend fun getMeal(
        @Query("year") year: Int,
        @Query("month") month: Int,
        @Query("day") day: Int
    ): Response<MealResponse>

    @GET(DodamUrl.Meal.CALORIE)
    suspend fun getCalorieOfMeal(
        @Query("year") year: Int,
        @Query("month") month: Int,
        @Query("day") day: Int
    ): Response<Calorie>

    @GET(DodamUrl.Meal.MONTH)
    suspend fun getMealOfMonth(
        @Query("month") month: Int,
        @Query("year") year: Int
    ): Response<List<MealResponse>>

    @GET(DodamUrl.Meal.Calorie.MONTH)
    suspend fun getCalorieOfMonth(
        @Query("year") year: Int,
        @Query("month") month: Int
    ): Response<List<Calorie>>
}
