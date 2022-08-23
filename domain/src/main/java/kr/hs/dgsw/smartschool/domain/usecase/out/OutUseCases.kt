package kr.hs.dgsw.smartschool.domain.usecase.out

data class OutUseCases(
    val getOut: GetOut,
    val getOutAllows: GetOutAllows,
    val getOutSleepingById: GetOutSleepingById,
    val getOutGoingById: GetOutGoingById,
    val postOutGoing: PostOutGoing,
    val postOutSleeping: PostOutSleeping
)
