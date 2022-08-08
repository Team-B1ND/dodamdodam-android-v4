package kr.hs.dgsw.smartschool.dodamdodam.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.hs.dgsw.smartschool.data.network.interceptor.ErrorResponseInterceptor
import kr.hs.dgsw.smartschool.data.network.interceptor.TokenInterceptor
import kr.hs.dgsw.smartschool.data.util.Constants
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
        return gsonBuilder.create()
    }

    @Provides
    @Singleton
    fun provideHttpClient(tokenInterceptor: TokenInterceptor): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        //val errorResponseInterceptor = ErrorResponseInterceptor()
        val okhttpBuilder = OkHttpClient().newBuilder()
            .addInterceptor(interceptor)
            .addInterceptor(tokenInterceptor)
            //.addInterceptor(errorResponseInterceptor)
        return okhttpBuilder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(Constants.SERVER_HOST)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .callbackExecutor(Executors.newSingleThreadExecutor())
            .build()
    }

}