package kr.hs.dgsw.smartschool.dodamdodam.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.hs.dgsw.smartschool.data.repository.BusRepositoryImpl
import kr.hs.dgsw.smartschool.data.repository.MealRepositoryImpl
import kr.hs.dgsw.smartschool.domain.repository.BusRepository
import kr.hs.dgsw.smartschool.domain.repository.MealRepository
import kr.hs.dgsw.smartschool.data.repository.*
import kr.hs.dgsw.smartschool.domain.repository.*
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
    fun provideSignInRepository(signInRepositoryImpl: SignInRepositoryImpl): SignInRepository = signInRepositoryImpl

    @Singleton
    @Provides
    fun providesSignUpRepository(signUpRepositoryImpl: SignUpRepositoryImpl): SignUpRepository = signUpRepositoryImpl

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
    fun provideLocationRepository(locationRepositoryImpl: LocationRepositoryImpl): LocationRepository = locationRepositoryImpl

    @Singleton
    @Provides
    fun provideTimeRepository(timeRepositoryImpl: TimeRepositoryImpl): TimeRepository = timeRepositoryImpl

    @Singleton
    @Provides
    fun providePlaceRepository(placeRepositoryImpl: PlaceRepositoryImpl): PlaceRepository = placeRepositoryImpl
}