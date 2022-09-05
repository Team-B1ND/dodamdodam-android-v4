package kr.hs.dgsw.smartschool.domain.usecase.lostfound

data class LostFoundUseCases(
    val deleteLostFoundComment : DeleteLostFoundCommentUseCase,
    val deleteLostFound: DeleteLostFoundUseCase,
    val getLostFoundComment : GetLostFoundCommentUseCase,
    val getLostFound : GetLostFoundUseCase,
    val hideLostFound : HideLostFoundUseCase,
    val addLostFoundComment : AddLostFoundComment,
    val addLostFound: AddLostFound,
    val modifyLostFoundComment : ModifyLostFoundCommentUseCase,
    val modifyLostFound: PutLostFoundUseCase,
    val searchLostFoundUseCase: SearchLostFoundUseCase
)
