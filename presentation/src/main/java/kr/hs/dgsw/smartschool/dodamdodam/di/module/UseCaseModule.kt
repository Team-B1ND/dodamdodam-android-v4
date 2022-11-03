package kr.hs.dgsw.smartschool.dodamdodam.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.hs.dgsw.smartschool.domain.repository.AccountRepository
import kr.hs.dgsw.smartschool.domain.repository.BusRepository
import kr.hs.dgsw.smartschool.domain.repository.DataSetUpRepository
import kr.hs.dgsw.smartschool.domain.repository.FileUploadRepository
import kr.hs.dgsw.smartschool.domain.repository.ItMapRepository
import kr.hs.dgsw.smartschool.domain.repository.LostFoundRepository
import kr.hs.dgsw.smartschool.domain.repository.MealRepository
import kr.hs.dgsw.smartschool.domain.repository.OutRepository
import kr.hs.dgsw.smartschool.domain.repository.SongRepository
import kr.hs.dgsw.smartschool.domain.repository.StudentRepository
import kr.hs.dgsw.smartschool.domain.repository.StudyRoomRepository
import kr.hs.dgsw.smartschool.domain.repository.TimeRepository
import kr.hs.dgsw.smartschool.domain.repository.TokenRepository
import kr.hs.dgsw.smartschool.domain.repository.YouTubeRepository
import kr.hs.dgsw.smartschool.domain.usecase.account.AccountUseCases
import kr.hs.dgsw.smartschool.domain.usecase.account.DeleteAccount
import kr.hs.dgsw.smartschool.domain.usecase.account.GetAccount
import kr.hs.dgsw.smartschool.domain.usecase.bus.AddBus
import kr.hs.dgsw.smartschool.domain.usecase.bus.AddBusApply
import kr.hs.dgsw.smartschool.domain.usecase.bus.BusUseCases
import kr.hs.dgsw.smartschool.domain.usecase.bus.DeleteBus
import kr.hs.dgsw.smartschool.domain.usecase.bus.DeleteBusApply
import kr.hs.dgsw.smartschool.domain.usecase.bus.GetApplyBus
import kr.hs.dgsw.smartschool.domain.usecase.bus.GetBusList
import kr.hs.dgsw.smartschool.domain.usecase.bus.GetMyBusByMonth
import kr.hs.dgsw.smartschool.domain.usecase.bus.UpdateBusApply
import kr.hs.dgsw.smartschool.domain.usecase.bus.UpdateBusInfo
import kr.hs.dgsw.smartschool.domain.usecase.itmap.GetAllCompanies
import kr.hs.dgsw.smartschool.domain.usecase.itmap.GetCompanyById
import kr.hs.dgsw.smartschool.domain.usecase.itmap.ItMapUseCases
import kr.hs.dgsw.smartschool.domain.usecase.lostfound.AddLostFound
import kr.hs.dgsw.smartschool.domain.usecase.lostfound.AddLostFoundComment
import kr.hs.dgsw.smartschool.domain.usecase.lostfound.DeleteLostFound
import kr.hs.dgsw.smartschool.domain.usecase.lostfound.DeleteLostFoundComment
import kr.hs.dgsw.smartschool.domain.usecase.lostfound.GetLostFound
import kr.hs.dgsw.smartschool.domain.usecase.lostfound.GetLostFoundById
import kr.hs.dgsw.smartschool.domain.usecase.lostfound.GetLostFoundComment
import kr.hs.dgsw.smartschool.domain.usecase.lostfound.GetMyLostFound
import kr.hs.dgsw.smartschool.domain.usecase.lostfound.LostFoundUseCases
import kr.hs.dgsw.smartschool.domain.usecase.lostfound.ModifyLostFound
import kr.hs.dgsw.smartschool.domain.usecase.lostfound.ModifyLostFoundComment
import kr.hs.dgsw.smartschool.domain.usecase.lostfound.SearchLostFound
import kr.hs.dgsw.smartschool.domain.usecase.meal.GetCalorieOfMeal
import kr.hs.dgsw.smartschool.domain.usecase.meal.GetMeal
import kr.hs.dgsw.smartschool.domain.usecase.meal.MealUseCases
import kr.hs.dgsw.smartschool.domain.usecase.member.GetMyInfo
import kr.hs.dgsw.smartschool.domain.usecase.member.MemberUseCases
import kr.hs.dgsw.smartschool.domain.usecase.member.ModifyMemberInfo
import kr.hs.dgsw.smartschool.domain.usecase.out.ApplyOutGoing
import kr.hs.dgsw.smartschool.domain.usecase.out.ApplyOutSleeping
import kr.hs.dgsw.smartschool.domain.usecase.out.DeleteOutGoing
import kr.hs.dgsw.smartschool.domain.usecase.out.DeleteOutSleeping
import kr.hs.dgsw.smartschool.domain.usecase.out.GetAllOut
import kr.hs.dgsw.smartschool.domain.usecase.out.GetOutGoingById
import kr.hs.dgsw.smartschool.domain.usecase.out.GetOutSleepingById
import kr.hs.dgsw.smartschool.domain.usecase.out.ModifyOutGoing
import kr.hs.dgsw.smartschool.domain.usecase.out.ModifyOutSleeping
import kr.hs.dgsw.smartschool.domain.usecase.out.OutUseCases
import kr.hs.dgsw.smartschool.domain.usecase.setup.DataSetUp
import kr.hs.dgsw.smartschool.domain.usecase.setup.SetUpUseCases
import kr.hs.dgsw.smartschool.domain.usecase.setup.TeacherSetUp
import kr.hs.dgsw.smartschool.domain.usecase.song.ApplySong
import kr.hs.dgsw.smartschool.domain.usecase.song.DeleteSong
import kr.hs.dgsw.smartschool.domain.usecase.song.GetAllowSong
import kr.hs.dgsw.smartschool.domain.usecase.song.GetMelonChart
import kr.hs.dgsw.smartschool.domain.usecase.song.GetMySong
import kr.hs.dgsw.smartschool.domain.usecase.song.GetPendingSong
import kr.hs.dgsw.smartschool.domain.usecase.song.GetYouTubeVideo
import kr.hs.dgsw.smartschool.domain.usecase.song.SongUseCases
import kr.hs.dgsw.smartschool.domain.usecase.studyroom.ApplyStudyRoom
import kr.hs.dgsw.smartschool.domain.usecase.studyroom.CancelStudyRoom
import kr.hs.dgsw.smartschool.domain.usecase.studyroom.CreateDefaultStudyRoom
import kr.hs.dgsw.smartschool.domain.usecase.studyroom.CreateDefaultStudyRoomByWeekType
import kr.hs.dgsw.smartschool.domain.usecase.studyroom.GetDefaultStudyRoom
import kr.hs.dgsw.smartschool.domain.usecase.studyroom.GetMyStudyRoom
import kr.hs.dgsw.smartschool.domain.usecase.studyroom.GetStudyRoomById
import kr.hs.dgsw.smartschool.domain.usecase.studyroom.ModifyAppliedStudyRoom
import kr.hs.dgsw.smartschool.domain.usecase.studyroom.StudyRoomUseCases
import kr.hs.dgsw.smartschool.domain.usecase.time.GetAllTime
import kr.hs.dgsw.smartschool.domain.usecase.time.GetStartTime
import kr.hs.dgsw.smartschool.domain.usecase.time.TimeUseCases
import kr.hs.dgsw.smartschool.domain.usecase.token.DeleteToken
import kr.hs.dgsw.smartschool.domain.usecase.token.GetToken
import kr.hs.dgsw.smartschool.domain.usecase.token.TokenUseCases
import kr.hs.dgsw.smartschool.domain.usecase.token.UpdateNewToken
import kr.hs.dgsw.smartschool.domain.usecase.upload.UploadFileUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    @Singleton
    fun provideMealUseCases(repository: MealRepository): MealUseCases =
        MealUseCases(
            getMeal = GetMeal(repository),
            getCalorieOfMeal = GetCalorieOfMeal(repository)
        )

    @Provides
    @Singleton
    fun provideLostFoundUseCases(repository: LostFoundRepository): LostFoundUseCases =
        LostFoundUseCases(
            getLostFoundById = GetLostFoundById(repository),
            getLostFoundComment = GetLostFoundComment(repository),
            getLostFound = GetLostFound(repository),
            getMyLostFound = GetMyLostFound(repository),
            modifyLostFound = ModifyLostFound(repository),
            modifyLostFoundComment = ModifyLostFoundComment(repository),
            addLostFound = AddLostFound(repository),
            addLostFoundComment = AddLostFoundComment(repository),
            deleteLostFoundComment = DeleteLostFoundComment(repository),
            deleteLostFound = DeleteLostFound(repository),
            searchLostFound = SearchLostFound(repository)
        )

    @Provides
    @Singleton
    fun provideBusUseCases(repository: BusRepository): BusUseCases =
        BusUseCases(
            getBus = GetBusList(repository),
            getMyBusMonth = GetMyBusByMonth(repository),
            addBus = AddBus(repository),
            addBusApply = AddBusApply(repository),
            updateBusApply = UpdateBusApply(repository),
            updateBusInfo = UpdateBusInfo(repository),
            deleteBus = DeleteBus(repository),
            deleteBusApply = DeleteBusApply(repository),
            getMyBus = GetApplyBus(repository)
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
    fun provideMemberUseCases(studentRepository: StudentRepository): MemberUseCases =
        MemberUseCases(
            getMyInfo = GetMyInfo(studentRepository),
            modifyMemberInfo = ModifyMemberInfo(studentRepository)
        )

    @Provides
    @Singleton
    fun provideLocationUseCases(studyRoomRepository: StudyRoomRepository): StudyRoomUseCases =
        StudyRoomUseCases(
            applyStudyRoom = ApplyStudyRoom(studyRoomRepository),
            modifyAppliedStudyRoom = ModifyAppliedStudyRoom(studyRoomRepository),
            getStudyRoomById = GetStudyRoomById(studyRoomRepository),
            cancelStudyRoom = CancelStudyRoom(studyRoomRepository),
            getDefaultStudyRoom = GetDefaultStudyRoom(studyRoomRepository),
            createDefaultStudyRoom = CreateDefaultStudyRoom(studyRoomRepository),
            createDefaultStudyRoomByWeekType = CreateDefaultStudyRoomByWeekType(studyRoomRepository),
            getMyStudyRoom = GetMyStudyRoom(studyRoomRepository)
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
    fun provideSongUseCases(songRepository: SongRepository, youTubeRepository: YouTubeRepository): SongUseCases =
        SongUseCases(
            getAllowSong = GetAllowSong(songRepository),
            getMySong = GetMySong(songRepository),
            getPendingSong = GetPendingSong(songRepository),
            applySong = ApplySong(songRepository),
            getMelonChart = GetMelonChart(songRepository),
            deleteSong = DeleteSong(songRepository),
            getYouTubeVideo = GetYouTubeVideo(youTubeRepository)
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
            getAllOut = GetAllOut(outRepository),
            getOutSleepingById = GetOutSleepingById(outRepository),
            getOutGoingById = GetOutGoingById(outRepository),
            applyOutGoing = ApplyOutGoing(outRepository),
            applyOutSleeping = ApplyOutSleeping(outRepository),
            deleteOutGoing = DeleteOutGoing(outRepository),
            deleteOutSleeping = DeleteOutSleeping(outRepository),
            modifyOutGoing = ModifyOutGoing(outRepository),
            modifyOutSleeping = ModifyOutSleeping(outRepository)
        )

    @Provides
    @Singleton
    fun provideItMapUseCases(itMapRepository: ItMapRepository): ItMapUseCases =
        ItMapUseCases(
            getAllCompanies = GetAllCompanies(itMapRepository),
            getCompanyById = GetCompanyById(itMapRepository)
        )

    @Provides
    @Singleton
    fun provideUploadFileUseCase(fileUploadRepository: FileUploadRepository): UploadFileUseCase =
        UploadFileUseCase(fileUploadRepository)
}
