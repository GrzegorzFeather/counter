package com.feathersoft.counter.core.architecture

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Retrofit
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

  @Inject
  lateinit var retrofit: Retrofit

  override fun onCreate(savedInstanceState: Bundle?) {
//    CounterApplication.appComponent.inject(this@BaseActivity)
    super.onCreate(savedInstanceState)
  }

}