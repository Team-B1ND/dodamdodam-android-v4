package kr.hs.dgsw.smartschool.dodamdodam.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.hs.dgsw.smartschool.data.repository.AccountRepositoryImpl
import kr.hs.dgsw.smartschool.data.repository.AuthRepositoryImpl
import kr.hs.dgsw.smartschool.data.repository.BusRepositoryImpl
import kr.hs.dgsw.smartschool.data.repository.ClassInfoRepositoryImpl
import kr.hs.dgsw.smartschool.data.repository.DataSetUpRepositoryImpl
import kr.hs.dgsw.smartschool.data.repository.FileUploadRepositoryImpl
import kr.hs.dgsw.smartschool.data.repository.StudyRoomRepositoryImpl
import kr.hs.dgsw.smartschool.data.repository.MealRepositoryImpl
import kr.hs.dgsw.smartschool.data.repository.OutRepositoryImpl
import kr.hs.dgsw.smartschool.data.repository.PlaceRepositoryImpl
import kr.hs.dgsw.smartschool.data.repository.PointRepositoryImpl
import kr.hs.dgsw.smartschool.data.repository.SongRepositoryImpl
import kr.hs.dgsw.smartschool.data.repository.StudentRepositoryImpl
import kr.hs.dgsw.smartschool.data.repository.TeacherRepositoryImpl
import kr.hs.dgsw.smartschool.data.repository.TimeRepositoryImpl
import kr.hs.dgsw.smartschool.data.repository.TokenRepositoryImpl
import kr.hs.dgsw.smartschool.domain.repository.AccountRepository
import kr.hs.dgsw.smartschool.domain.repository.AuthRepository
import kr.hs.dgsw.smartschool.domain.repository.BusRepository
import kr.hs.dgsw.smartschool.domain.repository.ClassInfoRepository
import kr.hs.dgsw.smartschool.domain.repository.DataSetUpRepository
import kr.hs.dgsw.smartschool.domain.repository.FileUploadRepository
import kr.hs.dgsw.smartschool.domain.repository.StudyRoomRepository
import kr.hs.dgsw.smartschool.domain.repository.MealRepository
import kr.hs.dgsw.smartschool.domain.repository.OutRepository
import kr.hs.dgsw.smartschool.domain.repository.PlaceRepository
import kr.hs.dgsw.smartschool.domain.repository.PointRepository
import kr.hs.dgsw.smartschool.domain.repository.SongRepository
import kr.hs.dgsw.smartschool.domain.repository.StudentRepository
import kr.hs.dgsw.smartschool.domain.repository.TeacherRepository
import kr.hs.dgsw.smartschool.domain.repository.TimeRepository
import kr.hs.dgsw.smartschool.domain.repository.TokenRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideMealRepository(mealRepositoryImpl: MealRepositoryImpl): MealRepository = mealRepositoryImpl

    @Singleton
    @Provides
    fun provideBusRepository(busRepositoryImpl: BusRepositoryImpl): BusRepository = busRepositoryImpl

    @Singleton
    @Provides
    fun providesAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository = authRepositoryImpl

    @Singleton
    @Provides
    fun provideTokenRepository(tokenRepositoryImpl: TokenRepositoryImpl): TokenRepository = tokenRepositoryImpl

    @Singleton
    @Provides
    fun provideStudentRepository(studentRepositoryImpl: StudentRepositoryImpl): StudentRepository = studentRepositoryImpl

    @Singleton
    @Provides
    fun provideTeacherRepository(teacherRepositoryImpl: TeacherRepositoryImpl): TeacherRepository = teacherRepositoryImpl

    @Singleton
    @Provides
    fun provideFileUploadRepository(fileUploadRepositoryImpl: FileUploadRepositoryImpl): FileUploadRepository = fileUploadRepositoryImpl

    @Singleton
    @Provides
    fun providePointRepository(pointRepositoryImpl: PointRepositoryImpl): PointRepository = pointRepositoryImpl

    @Singleton
    @Provides
    fun provideLocationRepository(studyRoomRepositoryImpl: StudyRoomRepositoryImpl): StudyRoomRepository = studyRoomRepositoryImpl

    @Singleton
    @Provides
    fun provideTimeRepository(timeRepositoryImpl: TimeRepositoryImpl): TimeRepository = timeRepositoryImpl

    @Singleton
    @Provides
    fun providePlaceRepository(placeRepositoryImpl: PlaceRepositoryImpl): PlaceRepository = placeRepositoryImpl

    @Singleton
    @Provides
    fun provideClassInfoRepository(classInfoRepositoryImpl: ClassInfoRepositoryImpl): ClassInfoRepository = classInfoRepositoryImpl

    @Singleton
    @Provides
    fun provideDataSetUpRepository(dataSetUpRepositoryImpl: DataSetUpRepositoryImpl): DataSetUpRepository = dataSetUpRepositoryImpl

    @Singleton
    @Provides
    fun provideSongRepository(songRepositoryImpl: SongRepositoryImpl): SongRepository = songRepositoryImpl

    @Singleton
    @Provides
    fun provideAccountRepository(accountRepositoryImpl: AccountRepositoryImpl): AccountRepository = accountRepositoryImpl

    @Singleton
    @Provides
    fun provideOutRepository(outRepositoryImpl: OutRepositoryImpl): OutRepository = outRepositoryImpl
}
