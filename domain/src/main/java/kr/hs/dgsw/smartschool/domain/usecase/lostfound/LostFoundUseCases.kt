package kr.hs.dgsw.smartschool.domain.usecase.lostfound

data class LostFoundUseCases(
    val deleteLostFoundComment: DeleteLostFoundComment,
    val deleteLostFound: DeleteLostFound,
    val getLostFoundComment: GetLostFoundComment,
    val getLostFound: GetLostFound,
    val getLostFoundAll: GetLostFoundAll,
    val getMyLostFound: GetMyLostFound,
    val addLostFoundComment: AddLostFoundComment,
    val addLostFound: AddLostFound,
    val modifyLostFoundComment: ModifyLostFoundComment,
    val modifyLostFound: ModifyLostFound,
    val searchLostFound: SearchLostFound,
    val getLostFoundById: GetLostFoundById
)
