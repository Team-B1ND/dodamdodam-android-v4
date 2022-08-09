package kr.hs.dgsw.smartschool.dodamdodam.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.hs.dgsw.smartschool.data.database.sharedpreferences.SharedPreferenceManager
import kr.hs.dgsw.smartschool.data.network.api.*
import kr.hs.dgsw.smartschool.data.network.remote.BusRemote
import kr.hs.dgsw.smartschool.data.network.remote.MealRemote
import kr.hs.dgsw.smartschool.data.network.remote.*
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.domain.model.place.Place
import kr.hs.dgsw.smartschool.domain.repository.SongRepository
import retrofit2.Retrofit
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

    @Singleton
    @Provides
    fun provideSignInRemote(): SignInRemote = SignInRemote()

    @Singleton
    @Provides
    fun provideSignUpRemote(): SignUpRemote = SignUpRemote()
    
    @Singleton
    @Provides
    fun provideTokenRemote(): TokenRemote = TokenRemote()

    @Singleton
    @Provides
    fun provideMemberRemote(retrofit: Retrofit): MemberRemote =
        MemberRemote(retrofit.create(MemberApi::class.java))

    @Singleton
    @Provides
    fun provideFileUploadRemote(retrofit: Retrofit): FileUploadRemote =
        FileUploadRemote(retrofit.create(FileUploadApi::class.java))

    @Singleton
    @Provides
    fun providePointRemote(retrofit: Retrofit): PointRemote =
        PointRemote(retrofit.create(PointApi::class.java))

    @Singleton
    @Provides
    fun provideTimeRemote(retrofit: Retrofit): TimeRemote =
        TimeRemote(retrofit.create(TimeApi::class.java))

    @Singleton
    @Provides
    fun providePlaceRemote(retrofit: Retrofit): PlaceRemote =
        PlaceRemote(retrofit.create(PlaceApi::class.java))

    @Singleton
    @Provides
    fun provideLocationRemote(retrofit: Retrofit): LocationRemote =
        LocationRemote(retrofit.create(LocationApi::class.java))

    @Singleton
    @Provides
    fun provideClassInfoRemote(retrofit: Retrofit): ClassInfoRemote =
        ClassInfoRemote(retrofit.create(ClassInfoApi::class.java))

    @Singleton
    @Provides
    fun provideSongRemote(retrofit: Retrofit, context: Context): SongRemote =
        SongRemote(retrofit.create(SongApi::class.java),
            SharedPreferenceManager.getDefaultSharedPreferences(context)
                .getString(context.getString(R.string.pref_key_thumbnail), "default")!!)
}