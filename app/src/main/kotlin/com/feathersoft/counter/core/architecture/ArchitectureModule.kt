package com.feathersoft.counter.core.architecture

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ArchitectureModule {
  @Provides
  @Singleton
  fun provideGson() = GsonBuilder().create()
}