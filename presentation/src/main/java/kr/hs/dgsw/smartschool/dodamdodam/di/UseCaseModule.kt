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
import kr.hs.dgsw.smartschool.domain.usecase.meal.DeleteMeal
import kr.hs.dgsw.smartschool.domain.usecase.meal.GetAllMeal
import kr.hs.dgsw.smartschool.domain.usecase.meal.MealUseCases
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
            deleteBusApply = DeleteBusApply(repository)
        )
}