package kr.hs.dgsw.smartschool.dodamdodam.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.hs.dgsw.smartschool.data.database.sharedpreferences.SharedPreferenceManager
import kr.hs.dgsw.smartschool.data.network.api.BannerApi
import kr.hs.dgsw.smartschool.data.network.api.BusApi
import kr.hs.dgsw.smartschool.data.network.api.ClassInfoApi
import kr.hs.dgsw.smartschool.data.network.api.EveningStudyApi
import kr.hs.dgsw.smartschool.data.network.api.FileUploadApi
import kr.hs.dgsw.smartschool.data.network.api.ItMapApi
import kr.hs.dgsw.smartschool.data.network.api.LostFoundApi
import kr.hs.dgsw.smartschool.data.network.api.MealApi
import kr.hs.dgsw.smartschool.data.network.api.MemberApi
import kr.hs.dgsw.smartschool.data.network.api.OutApi
import kr.hs.dgsw.smartschool.data.network.api.PlaceApi
import kr.hs.dgsw.smartschool.data.network.api.PointApi
import kr.hs.dgsw.smartschool.data.network.api.SongApi
import kr.hs.dgsw.smartschool.data.network.api.StudyRoomApi
import kr.hs.dgsw.smartschool.data.network.api.TimeTableApi
import kr.hs.dgsw.smartschool.data.network.api.YouTubeApi
import kr.hs.dgsw.smartschool.data.network.remote.AuthRemote
import kr.hs.dgsw.smartschool.data.network.remote.BannerRemote
import kr.hs.dgsw.smartschool.data.network.remote.BusRemote
import kr.hs.dgsw.smartschool.data.network.remote.ClassInfoRemote
import kr.hs.dgsw.smartschool.data.network.remote.EveningStudyRemote
import kr.hs.dgsw.smartschool.data.network.remote.FileUploadRemote
import kr.hs.dgsw.smartschool.data.network.remote.ItMapRemote
import kr.hs.dgsw.smartschool.data.network.remote.LostFoundRemote
import kr.hs.dgsw.smartschool.data.network.remote.MealRemote
import kr.hs.dgsw.smartschool.data.network.remote.MemberRemote
import kr.hs.dgsw.smartschool.data.network.remote.OutRemote
import kr.hs.dgsw.smartschool.data.network.remote.PlaceRemote
import kr.hs.dgsw.smartschool.data.network.remote.PointRemote
import kr.hs.dgsw.smartschool.data.network.remote.SongRemote
import kr.hs.dgsw.smartschool.data.network.remote.StudyRoomRemote
import kr.hs.dgsw.smartschool.data.network.remote.TimeTableRemote
import kr.hs.dgsw.smartschool.data.network.remote.TokenRemote
import kr.hs.dgsw.smartschool.data.network.remote.YouTubeRemote
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.di.OtherRemoteRetrofit
import kr.hs.dgsw.smartschool.dodamdodam.di.YouTubeRemoteRetrofit
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {
    @Singleton
    @Provides
    fun provideMealRemote(@OtherRemoteRetrofit retrofit: Retrofit): MealRemote =
        MealRemote(retrofit.create(MealApi::class.java))

    @Singleton
    @Provides
    fun provideBusRemote(@OtherRemoteRetrofit retrofit: Retrofit): BusRemote =
        BusRemote(retrofit.create(BusApi::class.java))

    @Singleton
    @Provides
    fun provideLostFoundRemote(@OtherRemoteRetrofit retrofit: Retrofit): LostFoundRemote =
        LostFoundRemote(retrofit.create(LostFoundApi::class.java))

    @Singleton
    @Provides
    fun provideAuthRemote(): AuthRemote = AuthRemote()

    @Singleton
    @Provides
    fun provideYouTubeRemote(@YouTubeRemoteRetrofit retrofit: Retrofit): YouTubeRemote =
        YouTubeRemote(retrofit.create(YouTubeApi::class.java))

    @Singleton
    @Provides
    fun provideTokenRemote(): TokenRemote = TokenRemote()

    @Singleton
    @Provides
    fun provideMemberRemote(@OtherRemoteRetrofit retrofit: Retrofit): MemberRemote =
        MemberRemote(retrofit.create(MemberApi::class.java))

    @Singleton
    @Provides
    fun provideFileUploadRemote(@OtherRemoteRetrofit retrofit: Retrofit): FileUploadRemote =
        FileUploadRemote(retrofit.create(FileUploadApi::class.java))

    @Singleton
    @Provides
    fun providePointRemote(@OtherRemoteRetrofit retrofit: Retrofit): PointRemote =
        PointRemote(retrofit.create(PointApi::class.java))

    @Singleton
    @Provides
    fun provideTimeRemote(@OtherRemoteRetrofit retrofit: Retrofit): TimeTableRemote =
        TimeTableRemote(retrofit.create(TimeTableApi::class.java))

    @Singleton
    @Provides
    fun providePlaceRemote(@OtherRemoteRetrofit retrofit: Retrofit): PlaceRemote =
        PlaceRemote(retrofit.create(PlaceApi::class.java))

    @Singleton
    @Provides
    fun provideLocationRemote(@OtherRemoteRetrofit retrofit: Retrofit): StudyRoomRemote =
        StudyRoomRemote(retrofit.create(StudyRoomApi::class.java))

    @Singleton
    @Provides
    fun provideClassInfoRemote(@OtherRemoteRetrofit retrofit: Retrofit): ClassInfoRemote =
        ClassInfoRemote(retrofit.create(ClassInfoApi::class.java))

    @Singleton
    @Provides
    fun provideSongRemote(@OtherRemoteRetrofit retrofit: Retrofit, context: Context): SongRemote =
        SongRemote(
            retrofit.create(SongApi::class.java),
            SharedPreferenceManager.getDefaultSharedPreferences(context)
                .getString(context.getString(R.string.pref_key_thumbnail), "default")!!
        )

    @Singleton
    @Provides
    fun provideOutRemote(@OtherRemoteRetrofit retrofit: Retrofit): OutRemote =
        OutRemote(retrofit.create(OutApi::class.java))

    @Singleton
    @Provides
    fun provideEveningStudyRemote(@OtherRemoteRetrofit retrofit: Retrofit): EveningStudyRemote =
        EveningStudyRemote(retrofit.create(EveningStudyApi::class.java))

    @Singleton
    @Provides
    fun provideItMapRemote(@OtherRemoteRetrofit retrofit: Retrofit): ItMapRemote =
        ItMapRemote(retrofit.create(ItMapApi::class.java))

    @Singleton
    @Provides
    fun provideBannerRemote(@OtherRemoteRetrofit retrofit: Retrofit): BannerRemote =
        BannerRemote(retrofit.create(BannerApi::class.java))
}
