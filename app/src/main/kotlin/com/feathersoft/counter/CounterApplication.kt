package com.feathersoft.counter

import android.app.Application
import com.feathersoft.counter.core.architecture.AppComponent
import com.feathersoft.counter.core.architecture.ArchitectureModule
import com.feathersoft.counter.core.architecture.DaggerAppComponent
import com.feathersoft.counter.core.architecture.NetworkModule

class CounterApplication : Application() {

  companion object {
    lateinit var appComponent: AppComponent
  }

  override fun onCreate() {
    super.onCreate()
    appComponent = DaggerAppComponent
        .builder()
        .architectureModule(ArchitectureModule())
        .networkModule(NetworkModule("http://0.0.0.0:3000"))
        .build()
  }
}
