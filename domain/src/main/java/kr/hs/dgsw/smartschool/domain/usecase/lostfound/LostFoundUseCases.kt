package kr.hs.dgsw.smartschool.domain.usecase.lostfound

data class LostFoundUseCases(
    val deleteLostFoundComment : DeleteLostFoundCommentUseCase,
    val deleteLostFound: DeleteLostFoundUseCase,
    val getLostFoundComment : GetLostFoundCommentUseCase,
    val getLostFound : GetLostFoundUseCase,
    val hideLostFound : HideLostFoundUseCase,
    val postLostFoundComment : PostLostFoundComment,
    val postLostFound: PostLostFound,
    val putLostFoundComment : PutLostFoundCommentUseCase,
    val putLostFound: PutLostFoundUseCase,
    val searchLostFoundUseCase: SearchLostFoundUseCase
)
