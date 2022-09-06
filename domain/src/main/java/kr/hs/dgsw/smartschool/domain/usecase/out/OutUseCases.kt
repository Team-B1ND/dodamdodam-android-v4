package kr.hs.dgsw.smartschool.domain.usecase.out

data class OutUseCases(
    val getOut: GetOut,
    val getOutByDate: GetOutByDate,
    val getOutSleepingById: GetOutSleepingById,
    val getOutGoingById: GetOutGoingById,
    val applyOutGoing: ApplyOutGoing,
    val applyOutSleeping: ApplyOutSleeping,
    val deleteOutGoing: DeleteOutGoing,
    val deleteOutSleeping: DeleteOutSleeping,
    val modifyOutGoing: ModifyOutGoing,
    val modifyOutSleeping: ModifyOutSleeping
)
