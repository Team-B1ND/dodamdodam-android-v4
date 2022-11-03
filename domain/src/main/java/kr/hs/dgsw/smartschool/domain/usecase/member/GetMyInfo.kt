package kr.hs.dgsw.smartschool.domain.usecase.member

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.model.member.Student
import kr.hs.dgsw.smartschool.domain.repository.StudentRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class GetMyInfo @Inject constructor(
    val repository: StudentRepository
) : BaseUseCase<Unit, Student>() {
    override fun invoke(params: Unit): Flow<Resource<Student>> = execute {
        repository.getMyInfo()
    }
}
