package kr.hs.dgsw.smartschool.data.network.remote

import kr.hs.dgsw.smartschool.data.base.remote.BaseRemote
import kr.hs.dgsw.smartschool.data.network.api.MealApi
import kr.hs.dgsw.smartschool.domain.model.meal.Meal
import javax.inject.Inject

class MealRemote @Inject constructor(
    override val api: MealApi
) : BaseRemote<MealApi>() {

    suspend fun getMeal(year: Int, month: Int, day: Int): Meal {
        return api.getMeal(year, month, day).data
    }

    suspend fun getCalorieOfMeal(): String {
        return api.getCalorieOfMeal().data
    }
}
