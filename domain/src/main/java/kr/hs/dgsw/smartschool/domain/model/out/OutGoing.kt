package kr.hs.dgsw.smartschool.domain.model.out

import java.util.Date


class OutGoing(
    idx: Int,
    studentIdx: Int,
    startTime: Date,
    endTime: Date,
    isAllow: Int,
    reason: String,
    allowTeacherIdx: Int,
    allowTeacherTime: Date,
    createdAt: Date,
): OutItem(
    idx,
    studentIdx,
    startTime,
    endTime,
    isAllow,
    reason,
    allowTeacherIdx,
    allowTeacherTime,
    createdAt
)