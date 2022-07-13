package kr.hs.dgsw.smartschool.dodamdodam.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.hs.dgsw.smartschool.data.repository.BusRepositoryImpl
import kr.hs.dgsw.smartschool.data.repository.MealRepositoryImpl
import kr.hs.dgsw.smartschool.domain.repository.BusRepository
import kr.hs.dgsw.smartschool.domain.repository.MealRepository
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
}