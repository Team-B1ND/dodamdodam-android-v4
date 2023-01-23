package kr.hs.dgsw.smartschool.dodamdodam.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.hs.dgsw.smartschool.data.network.interceptor.TokenInterceptor
import kr.hs.dgsw.smartschool.data.network.url.DodamUrl
import kr.hs.dgsw.smartschool.data.util.Constants
import kr.hs.dgsw.smartschool.dodamdodam.di.OtherOkHttpClient
import kr.hs.dgsw.smartschool.dodamdodam.di.OtherRemoteRetrofit
import kr.hs.dgsw.smartschool.dodamdodam.di.YouTubeOkHttpClient
import kr.hs.dgsw.smartschool.dodamdodam.di.YouTubeRemoteRetrofit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
        return gsonBuilder.create()
    }

    @OtherOkHttpClient
    @Provides
    @Singleton
    fun provideHttpClient(tokenInterceptor: TokenInterceptor): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val okhttpBuilder = OkHttpClient().newBuilder()
            .addInterceptor(interceptor)
            .addInterceptor(tokenInterceptor)
        return okhttpBuilder.build()
    }

    @YouTubeOkHttpClient
    @Provides
    @Singleton
    fun provideYouTubeHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val okhttpBuilder = OkHttpClient().newBuilder()
            .addInterceptor(interceptor)
        return okhttpBuilder.build()
    }

    @OtherRemoteRetrofit
    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, @OtherOkHttpClient okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(DodamUrl.SERVER_HOST)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .callbackExecutor(Executors.newSingleThreadExecutor())
            .build()
    }

    @YouTubeRemoteRetrofit
    @Provides
    @Singleton
    fun provideYouTubeRetrofit(gson: Gson, @YouTubeOkHttpClient okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(DodamUrl.YOUTUBE_HOST)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .callbackExecutor(Executors.newSingleThreadExecutor())
            .build()
    }
}
