package kr.hs.dgsw.smartschool.dodamdodam.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.hs.dgsw.smartschool.data.database.sharedpreferences.SharedPreferenceManager
import kr.hs.dgsw.smartschool.data.network.api.BusApi
import kr.hs.dgsw.smartschool.data.network.api.ClassInfoApi
import kr.hs.dgsw.smartschool.data.network.api.FileUploadApi
import kr.hs.dgsw.smartschool.data.network.api.MealApi
import kr.hs.dgsw.smartschool.data.network.api.MemberApi
import kr.hs.dgsw.smartschool.data.network.api.OutApi
import kr.hs.dgsw.smartschool.data.network.api.PlaceApi
import kr.hs.dgsw.smartschool.data.network.api.PointApi
import kr.hs.dgsw.smartschool.data.network.api.SongApi
import kr.hs.dgsw.smartschool.data.network.api.StudyRoomApi
import kr.hs.dgsw.smartschool.data.network.api.TimeTableApi
import kr.hs.dgsw.smartschool.data.network.remote.AuthRemote
import kr.hs.dgsw.smartschool.data.network.remote.BusRemote
import kr.hs.dgsw.smartschool.data.network.remote.ClassInfoRemote
import kr.hs.dgsw.smartschool.data.network.remote.FileUploadRemote
import kr.hs.dgsw.smartschool.data.network.remote.MealRemote
import kr.hs.dgsw.smartschool.data.network.remote.MemberRemote
import kr.hs.dgsw.smartschool.data.network.remote.OutRemote
import kr.hs.dgsw.smartschool.data.network.remote.PlaceRemote
import kr.hs.dgsw.smartschool.data.network.remote.PointRemote
import kr.hs.dgsw.smartschool.data.network.remote.SongRemote
import kr.hs.dgsw.smartschool.data.network.remote.StudyRoomRemote
import kr.hs.dgsw.smartschool.data.network.remote.TimeTableRemote
import kr.hs.dgsw.smartschool.data.network.remote.TokenRemote
import kr.hs.dgsw.smartschool.dodamdodam.R
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
    fun provideLostFoundRemote(retrofit: Retrofit): LostFoundRemote =
        LostFoundRemote(retrofit.create(LostFoundApi::class.java))

    @Singleton
    @Provides
    fun provideBusRemote(retrofit: Retrofit): BusRemote =
        BusRemote(retrofit.create(BusApi::class.java))

    @Singleton
    @Provides
    fun provideAuthRemote(): AuthRemote = AuthRemote()

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
    fun provideTimeRemote(retrofit: Retrofit): TimeTableRemote =
        TimeTableRemote(retrofit.create(TimeTableApi::class.java))

    @Singleton
    @Provides
    fun providePlaceRemote(retrofit: Retrofit): PlaceRemote =
        PlaceRemote(retrofit.create(PlaceApi::class.java))

    @Singleton
    @Provides
    fun provideLocationRemote(retrofit: Retrofit): StudyRoomRemote =
        StudyRoomRemote(retrofit.create(StudyRoomApi::class.java))

    @Singleton
    @Provides
    fun provideClassInfoRemote(retrofit: Retrofit): ClassInfoRemote =
        ClassInfoRemote(retrofit.create(ClassInfoApi::class.java))

    @Singleton
    @Provides
    fun provideSongRemote(retrofit: Retrofit, context: Context): SongRemote =
        SongRemote(
            retrofit.create(SongApi::class.java),
            SharedPreferenceManager.getDefaultSharedPreferences(context)
                .getString(context.getString(R.string.pref_key_thumbnail), "default")!!
        )

    @Singleton
    @Provides
    fun provideOutRemote(retrofit: Retrofit): OutRemote =
        OutRemote(retrofit.create(OutApi::class.java))
}
