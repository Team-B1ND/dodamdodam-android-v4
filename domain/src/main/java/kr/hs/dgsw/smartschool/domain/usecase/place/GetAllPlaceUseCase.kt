package kr.hs.dgsw.smartschool.domain.usecase.place

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.base.noParamBaseUseCase
import kr.hs.dgsw.smartschool.domain.model.place.Place
import kr.hs.dgsw.smartschool.domain.repository.PlaceRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class GetAllPlaceUseCase @Inject constructor(
    val repository: PlaceRepository
) : noParamBaseUseCase<List<Place>>() {
    override fun invoke(): Flow<Resource<List<Place>>> = execute {
        repository.getAllPlace()
    }
}
