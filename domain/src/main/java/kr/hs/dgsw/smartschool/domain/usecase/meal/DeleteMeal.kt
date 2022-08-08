package kr.hs.dgsw.smartschool.domain.usecase.meal

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.repository.MealRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class DeleteMeal @Inject constructor(
    val repository: MealRepository
): BaseUseCase<Unit, String>() {
    override operator fun invoke(params: Unit): Flow<Resource<String>> = execute {
        repository.deleteAllMeal()
        "버스를 삭제하였습니다."
    }
}