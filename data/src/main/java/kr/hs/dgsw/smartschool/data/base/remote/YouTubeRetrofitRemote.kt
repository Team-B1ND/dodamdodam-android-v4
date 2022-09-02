package kr.hs.dgsw.smartschool.data.base.remote

import kr.hs.dgsw.smartschool.data.util.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors

abstract class YouTubeRetrofitRemote<API> : BaseRemote<API>() {
    protected fun <T> createApi(service: Class<T>): T {
        return RETROFIT.create(service)
    }

    private val RETROFIT: Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(Constants.YOUTUBE_HOST)
        .addConverterFactory(GsonConverterFactory.create())
        .callbackExecutor(Executors.newSingleThreadExecutor())
        .build()

    private val client: OkHttpClient
        get() {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val okHttpBuilder = OkHttpClient().newBuilder().addInterceptor(interceptor)
            return okHttpBuilder.build()
        }
}
