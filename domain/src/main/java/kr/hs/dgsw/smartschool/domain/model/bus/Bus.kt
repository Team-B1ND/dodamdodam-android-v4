package kr.hs.dgsw.smartschool.domain.model.bus

data class Bus(
    val idx: Int,
    val busName : String, // 버스 이름
    val description: String, // 버스 상세 설명
    val peopleLimit: Integer, // 최대 탑승 가능 인원
    val leaveTime: String, // 출발 날짜  시간 (YYYY-MM-DD HH:mm:ss)
    val timeRequired: String, // 소요시간 (HH:mm:ss)
    val busMemberlength: Integer, // 현재 탑승 예정 인원

// ** NOTICE: 선생님 혹은 관리자 일 떄만 존재하는 컬럼 **
//val busMember: List<BusMember>
)
