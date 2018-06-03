package com.feathersoft.counter.core.architecture

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule(private val baseUrl: String) {

  @Provides
  @Singleton
  fun provideOkHttpClient() =
      OkHttpClient.Builder()
          .addInterceptor(HttpLoggingInterceptor())
          .build()

  @Provides
  @Singleton
  fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson) =
      Retrofit.Builder()
          .baseUrl(baseUrl)
          .client(okHttpClient)
          .addConverterFactory(GsonConverterFactory.create(gson))
          .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
          .build()
}