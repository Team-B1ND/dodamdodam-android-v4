package kr.hs.dgsw.smartschool.domain.usecase.location

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.repository.LocationRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class DeleteLocation @Inject constructor(
    val repository: LocationRepository
) : BaseUseCase<Int, String>() {
    override fun invoke(params: Int): Flow<Resource<String>> = execute {
        repository.deleteLocation(params)
    }
}
