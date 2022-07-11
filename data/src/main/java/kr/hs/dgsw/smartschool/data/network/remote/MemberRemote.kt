package kr.hs.dgsw.smartschool.data.network.remote

import kr.hs.dgsw.smartschool.data.base.remote.BaseRemote
import kr.hs.dgsw.smartschool.data.network.api.MemberApi
import javax.inject.Inject

class MemberRemote @Inject constructor(
    override val api: MemberApi
) : BaseRemote<MemberApi>() {
    

}