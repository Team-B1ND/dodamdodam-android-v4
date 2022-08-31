package kr.hs.dgsw.smartschool.dodamdodam.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.hs.dgsw.smartschool.domain.repository.*
import kr.hs.dgsw.smartschool.domain.usecase.account.AccountUseCases
import kr.hs.dgsw.smartschool.domain.usecase.account.DeleteAccount
import kr.hs.dgsw.smartschool.domain.usecase.account.GetAccount
import kr.hs.dgsw.smartschool.domain.usecase.bus.AddBus
import kr.hs.dgsw.smartschool.domain.usecase.bus.AddBusApply
import kr.hs.dgsw.smartschool.domain.usecase.bus.BusUseCases
import kr.hs.dgsw.smartschool.domain.usecase.bus.DeleteBus
import kr.hs.dgsw.smartschool.domain.usecase.bus.DeleteBusApply
import kr.hs.dgsw.smartschool.domain.usecase.bus.GetBusList
import kr.hs.dgsw.smartschool.domain.usecase.bus.GetMyBus
import kr.hs.dgsw.smartschool.domain.usecase.bus.GetMyBusByMonth
import kr.hs.dgsw.smartschool.domain.usecase.bus.UpdateBusApply
import kr.hs.dgsw.smartschool.domain.usecase.bus.UpdateBusInfo
import kr.hs.dgsw.smartschool.domain.usecase.location.DeleteLocation
import kr.hs.dgsw.smartschool.domain.usecase.location.GetMyLocation
import kr.hs.dgsw.smartschool.domain.usecase.location.LocationUseCases
import kr.hs.dgsw.smartschool.domain.usecase.location.PostLocation
import kr.hs.dgsw.smartschool.domain.usecase.location.PutLocation
import kr.hs.dgsw.smartschool.domain.usecase.lostfound.*
import kr.hs.dgsw.smartschool.domain.usecase.meal.DeleteMeal
import kr.hs.dgsw.smartschool.domain.usecase.meal.GetAllMeal
import kr.hs.dgsw.smartschool.domain.usecase.meal.MealUseCases
import kr.hs.dgsw.smartschool.domain.usecase.member.ChangeMemberInfo
import kr.hs.dgsw.smartschool.domain.usecase.member.GetMyInfo
import kr.hs.dgsw.smartschool.domain.usecase.member.MemberUseCases
import kr.hs.dgsw.smartschool.domain.usecase.out.DeleteOutGoing
import kr.hs.dgsw.smartschool.domain.usecase.out.DeleteOutSleeping
import kr.hs.dgsw.smartschool.domain.usecase.out.GetOut
import kr.hs.dgsw.smartschool.domain.usecase.out.GetOutAllows
import kr.hs.dgsw.smartschool.domain.usecase.out.GetOutGoingById
import kr.hs.dgsw.smartschool.domain.usecase.out.GetOutSleepingById
import kr.hs.dgsw.smartschool.domain.usecase.out.OutUseCases
import kr.hs.dgsw.smartschool.domain.usecase.out.PostOutGoing
import kr.hs.dgsw.smartschool.domain.usecase.out.PostOutSleeping
import kr.hs.dgsw.smartschool.domain.usecase.point.GetMyPoint
import kr.hs.dgsw.smartschool.domain.usecase.point.GetMyPointTarget
import kr.hs.dgsw.smartschool.domain.usecase.point.PointUseCases
import kr.hs.dgsw.smartschool.domain.usecase.setup.DataSetUp
import kr.hs.dgsw.smartschool.domain.usecase.setup.SetUpUseCases
import kr.hs.dgsw.smartschool.domain.usecase.setup.TeacherSetUp
import kr.hs.dgsw.smartschool.domain.usecase.song.GetAllowSong
import kr.hs.dgsw.smartschool.domain.usecase.song.GetMelonChart
import kr.hs.dgsw.smartschool.domain.usecase.song.GetMySong
import kr.hs.dgsw.smartschool.domain.usecase.song.GetPendingSong
import kr.hs.dgsw.smartschool.domain.usecase.song.PostSong
import kr.hs.dgsw.smartschool.domain.usecase.song.SongUseCases
import kr.hs.dgsw.smartschool.domain.usecase.time.GetAllTime
import kr.hs.dgsw.smartschool.domain.usecase.time.GetStartTime
import kr.hs.dgsw.smartschool.domain.usecase.time.TimeUseCases
import kr.hs.dgsw.smartschool.domain.usecase.token.DeleteToken
import kr.hs.dgsw.smartschool.domain.usecase.token.GetToken
import kr.hs.dgsw.smartschool.domain.usecase.token.TokenUseCases
import kr.hs.dgsw.smartschool.domain.usecase.token.UpdateNewToken
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
    fun provideLostFoundUseCases(repository: LostFoundRepository): LostFoundUseCases =
        LostFoundUseCases(
            deleteLostFound = DeleteLostFoundUseCase(repository),
            deleteLostFoundComment = DeleteLostFoundCommentUseCase(repository),
            getLostFound = GetLostFoundUseCase(repository),
            getLostFoundComment = GetLostFoundCommentUseCase(repository),
            hideLostFound = HideLostFoundUseCase(repository),
            postLostFound = PostLostFound(repository),
            postLostFoundComment = PostLostFoundComment(repository),
            putLostFound = PutLostFoundUseCase(repository),
            putLostFoundComment = PutLostFoundCommentUseCase(repository),
            searchLostFoundUseCase = SearchLostFoundUseCase(repository)
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
            deleteBusApply = DeleteBusApply(repository)
        )

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

    @Provides
    @Singleton
    fun providePointUseCases(pointRepository: PointRepository): PointUseCases =
        PointUseCases(
            getMyPoint = GetMyPoint(pointRepository),
            getMyPointTarget = GetMyPointTarget(pointRepository)
        )

    @Provides
    @Singleton
    fun provideLocationUseCases(locationRepository: LocationRepository): LocationUseCases =
        LocationUseCases(
            getMyLocation = GetMyLocation(locationRepository),
            postLocation = PostLocation(locationRepository),
            putLocation = PutLocation(locationRepository),
            deleteLocation = DeleteLocation(locationRepository)
        )

    @Provides
    @Singleton
    fun provideSetUpDataUseCases(dataSetUpRepository: DataSetUpRepository): SetUpUseCases =
        SetUpUseCases(
            dataSetUp = DataSetUp(dataSetUpRepository),
            teacherSetUp = TeacherSetUp(dataSetUpRepository)
        )

    @Provides
    @Singleton
    fun provideTimeUseCases(timeRepository: TimeRepository): TimeUseCases =
        TimeUseCases(
            getAllTime = GetAllTime(timeRepository),
            getStartTime = GetStartTime(timeRepository)
        )

    @Provides
    @Singleton
    fun provideSongUseCases(songRepository: SongRepository): SongUseCases =
        SongUseCases(
            getAllowSong = GetAllowSong(songRepository),
            getMySong = GetMySong(songRepository),
            getPendingSong = GetPendingSong(songRepository),
            postSong = PostSong(songRepository),
            getMelonChart = GetMelonChart(songRepository)
        )

    @Provides
    @Singleton
    fun provideAccountUseCases(accountRepository: AccountRepository): AccountUseCases =
        AccountUseCases(
            getAccount = GetAccount(accountRepository),
            deleteAccount = DeleteAccount(accountRepository)
        )

    @Provides
    @Singleton
    fun provideOutUseCases(outRepository: OutRepository): OutUseCases =
        OutUseCases(
            getOut = GetOut(outRepository),
            getOutAllows = GetOutAllows(outRepository),
            getOutSleepingById = GetOutSleepingById(outRepository),
            getOutGoingById = GetOutGoingById(outRepository),
            postOutGoing = PostOutGoing(outRepository),
            postOutSleeping = PostOutSleeping(outRepository),
            deleteOutGoing = DeleteOutGoing(outRepository),
            deleteOutSleeping = DeleteOutSleeping(outRepository)
        )
}
