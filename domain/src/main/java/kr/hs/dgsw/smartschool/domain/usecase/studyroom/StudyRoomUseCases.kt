package kr.hs.dgsw.smartschool.domain.usecase.studyroom

data class StudyRoomUseCases(
    val applyStudyRoom: ApplyStudyRoom,
    val modifyAppliedStudyRoom: ModifyAppliedStudyRoom,
    val getStudyRoomById: GetStudyRoomById,
    val getDefaultStudyRoom: GetDefaultStudyRoom,
    val createDefaultStudyRoom: CreateDefaultStudyRoom,
    val createDefaultStudyRoomByWeekType: CreateDefaultStudyRoomByWeekType,
    val getMyStudyRoom: GetMyStudyRoom

)
