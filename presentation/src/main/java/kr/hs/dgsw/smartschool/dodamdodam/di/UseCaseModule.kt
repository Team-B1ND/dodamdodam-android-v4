package kr.hs.dgsw.smartschool.dodamdodam.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.hs.dgsw.b1nd.dodamdodam.domain.usecase.bus.*
import kr.hs.dgsw.smartschool.domain.repository.BusRepository
import kr.hs.dgsw.smartschool.domain.repository.MealRepository
import kr.hs.dgsw.smartschool.domain.usecase.bus.BusUseCases
import kr.hs.dgsw.smartschool.domain.usecase.bus.GetBusList
import kr.hs.dgsw.smartschool.domain.repository.StudentRepository
import kr.hs.dgsw.smartschool.domain.repository.TeacherRepository
import kr.hs.dgsw.smartschool.domain.repository.TokenRepository
import kr.hs.dgsw.smartschool.domain.usecase.meal.DeleteMeal
import kr.hs.dgsw.smartschool.domain.usecase.meal.GetAllMeal
import kr.hs.dgsw.smartschool.domain.usecase.meal.MealUseCases
import kr.hs.dgsw.smartschool.domain.usecase.member.ChangeMemberInfo
import kr.hs.dgsw.smartschool.domain.usecase.member.GetMyInfo
import kr.hs.dgsw.smartschool.domain.usecase.member.MemberUseCases
import kr.hs.dgsw.smartschool.domain.usecase.token.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    @Singleton
    fun provideMealUseCases(repository: MealRepository): MealUseCases =
        MealUseCases(
            getAllMeal = GetAllMeal(repository),
            deleteMeal = DeleteMeal(repository)
        )

    @Provides
    @Singleton
    fun provideBusUseCases(repository: BusRepository): BusUseCases =
        BusUseCases(
            getBus = GetBusList(repository),
            getMyBus = GetMyBus(repository),
            getMyBusMonth = GetMyBusByMonth(repository),
            addBus = AddBus(repository),
            addBusApply = AddBusApply(repository),
            updateBusApply = UpdateBusApply(repository),
            updateBusInfo = UpdateBusInfo(repository),
            deleteBus = DeleteBus(repository),
            deleteBusApply = DeleteBusApply(repository))

    @Provides
    @Singleton
    fun provideTokenUseCases(repository: TokenRepository): TokenUseCases =
        TokenUseCases(
            deleteToken = DeleteToken(repository),
            getToken = GetToken(repository),
            updateNewToken = UpdateNewToken(repository)
        )

    @Provides
    @Singleton
    fun provideMemberUseCases(studentRepository: StudentRepository, teacherRepository: TeacherRepository): MemberUseCases =
        MemberUseCases(
            getMyInfo = GetMyInfo(studentRepository),
            changeMemberInfo = ChangeMemberInfo(studentRepository)
        )
}