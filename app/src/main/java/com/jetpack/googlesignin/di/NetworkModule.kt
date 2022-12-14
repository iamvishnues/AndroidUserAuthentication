package com.jetpack.googlesignin.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.jetpack.googlesignin.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.JavaNetCookieJar
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okio.Timeout
import retrofit2.Retrofit
import java.net.CookieManager
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideCookieManager():CookieManager{
        return CookieManager()
    }

    @Provides
    @Singleton
    fun provideHttpClient(cookieManager: CookieManager):OkHttpClient{
        return OkHttpClient.Builder().
        readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15,TimeUnit.SECONDS)
            .cookieJar(JavaNetCookieJar(cookieManager)).build()
    }

    fun provideRetrofit(okHttpClient: OkHttpClient):Retrofit{
        val contentType= "application/json".toMediaType()
        return Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory(contentType)).build()
    }
}