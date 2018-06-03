package com.feathersoft.counter.core.model

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.http.GET

data class Counter(val name: String) {

  companion object {
    private const val path: String = "/counters"

    fun api(retrofit: Retrofit) = retrofit.create(Schema::class.java)
  }

  lateinit var id: String

  interface Schema {
    @GET(path)
    fun all(): Observable<List<Counter>>
  }
}