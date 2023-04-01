package kr.hs.dgsw.smartschool.data.mapper

import kr.hs.dgsw.smartschool.data.network.request.out.ModifyOutRequest
import kr.hs.dgsw.smartschool.data.network.request.out.OutRequest
import kr.hs.dgsw.smartschool.domain.param.out.ModifyOutParam
import kr.hs.dgsw.smartschool.domain.param.out.OutParam

fun OutParam.toRequest(): OutRequest = OutRequest(
    endOutDate = endOutDate,
    reason = reason,
    startOutDate = startOutDate
)

fun ModifyOutParam.toRequest(): ModifyOutRequest = ModifyOutRequest(
    endOutDate = endOutDate,
    outId = outId,
    reason = reason,
    startOutDate = startOutDate
)
