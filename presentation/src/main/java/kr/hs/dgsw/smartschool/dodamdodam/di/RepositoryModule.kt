package kr.hs.dgsw.smartschool.dodamdodam.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.hs.dgsw.smartschool.data.repository.MealRepositoryImpl
import kr.hs.dgsw.smartschool.data.repository.SignInRepositoryImpl
import kr.hs.dgsw.smartschool.data.repository.SignUpRepositoryImpl
import kr.hs.dgsw.smartschool.domain.repository.MealRepository
import kr.hs.dgsw.smartschool.domain.repository.SignInRepository
import kr.hs.dgsw.smartschool.domain.repository.SignUpRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideMealRepository(mealRepositoryImpl: MealRepositoryImpl): MealRepository = mealRepositoryImpl

    @Singleton
    @Provides
    fun provideSignInRepository(signInRepositoryImpl: SignInRepositoryImpl): SignInRepository = signInRepositoryImpl

    @Singleton
    @Provides
    fun providesSignUpRepository(signUpRepositoryImpl: SignUpRepositoryImpl): SignUpRepository = signUpRepositoryImpl
}