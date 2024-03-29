package kr.hs.dgsw.smartschool.data.network.remote

import kr.hs.dgsw.smartschool.data.base.remote.BaseRemote
import kr.hs.dgsw.smartschool.data.network.api.MealApi
import kr.hs.dgsw.smartschool.data.network.response.meal.MealResponse
import kr.hs.dgsw.smartschool.domain.model.meal.Calorie
import javax.inject.Inject

class MealRemote @Inject constructor(
    override val api: MealApi
) : BaseRemote<MealApi>() {

    suspend fun getMeal(year: Int, month: Int, day: Int): MealResponse {
        return api.getMeal(year, month, day).data
    }

    suspend fun getCalorieOfMeal(year: Int, month: Int, day: Int): Calorie {
        return api.getCalorieOfMeal(year, month, day).data
    }

    suspend fun getMealOfMonth(month: Int, year: Int): List<MealResponse> {
        return api.getMealOfMonth(month, year).data
    }

    suspend fun getCalorieOfMonth(year: Int, month: Int): List<Calorie> {
        return api.getCalorieOfMonth(year, month).data
    }
}
