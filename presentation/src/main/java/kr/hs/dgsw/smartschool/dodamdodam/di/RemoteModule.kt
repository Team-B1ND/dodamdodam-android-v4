package kr.hs.dgsw.smartschool.dodamdodam.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.hs.dgsw.smartschool.data.network.api.FileUploadApi
import kr.hs.dgsw.smartschool.data.network.api.MealApi
import kr.hs.dgsw.smartschool.data.network.api.MemberApi
import kr.hs.dgsw.smartschool.data.network.remote.*
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

}