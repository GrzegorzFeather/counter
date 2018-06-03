package com.feathersoft.counter.core.architecture

import com.feathersoft.counter.CounterApplication
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(
    modules = arrayOf(NetworkModule::class)
)
interface AppComponent {

  fun retrofit(): Retrofit

  fun inject(application: CounterApplication)
  fun inject(baseActivity: BaseActivity)
}