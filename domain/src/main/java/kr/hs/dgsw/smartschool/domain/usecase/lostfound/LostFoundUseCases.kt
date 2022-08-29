package kr.hs.dgsw.smartschool.domain.usecase.lostfound

import kr.hs.dgsw.smartschool.domain.model.lostfound.LostFound

data class LostFoundUseCases(
    val deleteLostFoundComment : DeleteLostFoundCommentUseCase,
    val deleteLostFound: DeleteLostFoundUseCase,
    val getLostFoundComment : GetLostFoundCommentUseCase,
    val getLostFound : GetLostFoundUseCase,
    val hideLostFound : HideLostFoundUseCase,
    val postLostFoundComment : PostLostFoundCommentUseCase,
    val postLostFound: PostLostFoundUseCase,
    val putLostFoundComment : PutLostFoundCommentUseCase,
    val putLostFound: PutLostFoundUseCase
)
