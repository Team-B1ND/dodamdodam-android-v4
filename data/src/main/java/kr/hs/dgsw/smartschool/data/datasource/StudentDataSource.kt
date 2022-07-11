package kr.hs.dgsw.smartschool.data.datasource

import kr.hs.dgsw.smartschool.data.base.BaseDataSource
import kr.hs.dgsw.smartschool.data.database.cache.MemberCache
import kr.hs.dgsw.smartschool.data.network.remote.MemberRemote
import javax.inject.Inject

class StudentDataSource @Inject constructor(
    override val remote: MemberRemote,
    override val cache: MemberCache
) : BaseDataSource<MemberRemote, MemberCache>() {

    // private val studentMapper = StudentMapper()

    



}