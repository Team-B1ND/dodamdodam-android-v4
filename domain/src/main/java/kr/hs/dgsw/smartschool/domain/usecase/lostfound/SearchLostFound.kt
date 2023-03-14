package kr.hs.dgsw.smartschool.domain.usecase.lostfound

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.UseCase
import kr.hs.dgsw.smartschool.domain.model.lostfound.LostFound
import kr.hs.dgsw.smartschool.domain.repository.LostFoundRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class SearchLostFound @Inject constructor(
    private val lostFoundRepository: LostFoundRepository
) : UseCase<SearchLostFound.Params, List<LostFound>>() {

    data class Params(val search: String)

    override fun invoke(params: Params): Flow<Resource<List<LostFound>>> = execute {
        lostFoundRepository.getLostFoundSearch(params.search)
    }
}
