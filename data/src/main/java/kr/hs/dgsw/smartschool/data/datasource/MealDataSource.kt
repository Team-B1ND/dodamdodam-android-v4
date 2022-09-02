package kr.hs.dgsw.smartschool.data.datasource

import kr.hs.dgsw.smartschool.data.base.BaseDataSource
import kr.hs.dgsw.smartschool.data.network.remote.MealRemote
import kr.hs.dgsw.smartschool.domain.model.meal.Meal
import javax.inject.Inject

class MealDataSource @Inject constructor(
    override val remote: MealRemote,
    override val cache: Any
) : BaseDataSource<MealRemote, Any> {

    suspend fun getMeal(year: Int, month: Int, day: Int): Meal =
        remote.getMeal(year, month, day)

    suspend fun getCalorieOfMeal(): String =
        remote.getCalorieOfMeal()
}
