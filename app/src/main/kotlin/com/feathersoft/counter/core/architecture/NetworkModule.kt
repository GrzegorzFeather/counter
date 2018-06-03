package com.feathersoft.counter.core.architecture

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class NetworkModule(private val baseUrl: String) {
  @Provides @Singleton fun provideRetrofit() =
      Retrofit.Builder()
          .baseUrl(baseUrl)
          .build()
}