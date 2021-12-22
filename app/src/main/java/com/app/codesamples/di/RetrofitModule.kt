package com.app.codesamples.di

import com.app.codesamples.BuildConfig
import com.app.codesamples.network.data.network.DataService
import com.app.codesamples.network.data.repository.DataRepositoryImpl
import com.app.codesamples.network.domain.repository.DataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {
    @Provides
    fun provideBaseUrl() = BuildConfig.BASE_URL

    @Provides
    @Singleton
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else OkHttpClient
        .Builder()
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        BASE_URL: String
    ): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    fun provideDataRepository(dataRepositoryImpl: DataRepositoryImpl): DataRepository = dataRepositoryImpl

    @Provides
    @Singleton
    fun provideDataService(retrofit: Retrofit): DataService = retrofit.create(DataService::class.java)

//    fun provideRepository(kanyewestapi:KanyeWestApi) : MainRepository = DefualtMainRepository(kanyewestapi)

}