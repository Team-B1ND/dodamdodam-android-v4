package kr.hs.dgsw.smartschool.data.network.remote

import android.util.Log
import kr.hs.dgsw.smartschool.data.base.BaseRemote
import kr.hs.dgsw.smartschool.data.network.api.MealApi
import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.data.network.response.data.MealData
import kr.hs.dgsw.smartschool.domain.model.meal.Meal
import javax.inject.Inject

class MealRemote @Inject constructor(
    override val api: MealApi
) : BaseRemote<MealApi>() {
    suspend fun getAllMeals(year: Int, month: Int): Response<List<Meal>> {
        return api.getAllMeals(year, month)
    }
}