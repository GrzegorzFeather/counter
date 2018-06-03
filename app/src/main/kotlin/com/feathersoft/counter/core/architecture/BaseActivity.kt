package com.feathersoft.counter.core.architecture

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.feathersoft.counter.CounterApplication
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import retrofit2.Retrofit
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

  @Inject
  lateinit var retrofit: Retrofit

  private val compositeDisposable = CompositeDisposable()

  override fun onCreate(savedInstanceState: Bundle?) {
    CounterApplication.appComponent.inject(this@BaseActivity)
    super.onCreate(savedInstanceState)
  }

  fun handleDisposable(disposable: Disposable) = compositeDisposable.add(disposable)
}