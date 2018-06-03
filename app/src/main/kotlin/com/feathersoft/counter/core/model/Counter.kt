package com.feathersoft.counter.core.model

data class Counter(val name: String) {

  lateinit var id: String

  companion object {
    const val path: String = "/counters"
  }

  interface Schema {
//    @GET(path)
  }

}