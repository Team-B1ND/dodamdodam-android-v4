package kr.hs.dgsw.smartschool.domain.usecase.setup

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.base.noParamBaseUseCase
import kr.hs.dgsw.smartschool.domain.repository.DataSetUpRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class TeacherSetUp @Inject constructor(
    val repository: DataSetUpRepository
) : noParamBaseUseCase<String>() {
    override operator fun invoke(): Flow<Resource<String>> = execute {
        repository.setUpTeacher()
        "선생님 정보 업데이터에 성공하였습니다."
    }
}
