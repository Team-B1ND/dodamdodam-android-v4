package kr.hs.dgsw.smartschool.dodamdodam.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.hs.dgsw.smartschool.data.network.api.BusApi
import kr.hs.dgsw.smartschool.data.network.api.MealApi
import kr.hs.dgsw.smartschool.data.network.remote.BusRemote
import kr.hs.dgsw.smartschool.data.network.remote.MealRemote
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {
    @Singleton
    @Provides
    fun provideMealRemote(retrofit: Retrofit): MealRemote =
        MealRemote(retrofit.create(MealApi::class.java))

    @Singleton
    @Provides
    fun provideBusRemote(retrofit: Retrofit): BusRemote =
        BusRemote(retrofit.create(BusApi::class.java))
}