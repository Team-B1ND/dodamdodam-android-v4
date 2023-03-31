package kr.hs.dgsw.smartschool.data.datasource

import kr.hs.dgsw.smartschool.data.base.BaseDataSource
import kr.hs.dgsw.smartschool.data.database.cache.MealCache
import kr.hs.dgsw.smartschool.data.mapper.toEntity
import kr.hs.dgsw.smartschool.data.mapper.toModel
import kr.hs.dgsw.smartschool.data.network.remote.MealRemote
import kr.hs.dgsw.smartschool.data.network.response.meal.MealResponse
import java.time.LocalDate
import javax.inject.Inject

class MealDataSource @Inject constructor(
    override val remote: MealRemote,
    override val cache: MealCache
) : BaseDataSource<MealRemote, MealCache> {

    suspend fun getMeal(date: String): MealResponse {
        val year = date.split('-')[0].toInt()
        val month = date.split('-')[1].toInt()
        val day = date.split('-')[2].toInt()

        return cache.getMeal(year, month, day)?.toModel()
            ?: getRemoteMealList(year, month).find { it.date == date }
            ?: MealResponse(null, date, null, false, null)
    }

    private suspend fun getRemoteMealList(year: Int, month: Int): List<MealResponse> =
        remote.getMealOfMonth(month, year).also {

            val localDate = LocalDate.of(year, month, 1)
            val size = localDate.lengthOfMonth()

            val mealMap = mutableMapOf<String, MealResponse>()
            val mealList = mutableListOf<MealResponse>()

            for (i in 1..size) {
                val date = String.format("%d-%02d-%02d", year, month, i)
                mealMap[date] = MealResponse(null, date, null, false, null)
            }

            it.forEach { meal ->
                mealMap[meal.date] = meal
            }
            mealMap.forEach { map ->
                mealList.add(map.value)
            }

            cache.insertMealList(
                mealList.toList().map { meal ->
                    meal.toEntity()
                }
            )
        }

    suspend fun getCalorieOfMeal(): String? =
        remote.getCalorieOfMeal()
}
