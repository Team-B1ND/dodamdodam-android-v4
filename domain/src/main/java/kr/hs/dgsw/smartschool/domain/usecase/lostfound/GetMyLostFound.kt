package kr.hs.dgsw.smartschool.domain.usecase.lostfound

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.NoParamUseCase
import kr.hs.dgsw.smartschool.domain.model.lostfound.LostFound
import kr.hs.dgsw.smartschool.domain.repository.LostFoundRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class GetMyLostFound @Inject constructor(
    private val lostFoundRepository: LostFoundRepository
) : NoParamUseCase<List<LostFound>>() {

    override fun invoke(): Flow<Resource<List<LostFound>>> = execute {
        Log.d("MyLostFoundUseCase", "실행")
        lostFoundRepository.getMyLostFound()
    }
}
