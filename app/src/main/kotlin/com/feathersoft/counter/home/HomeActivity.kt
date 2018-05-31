package com.feathersoft.counter.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.feathersoft.counter.R
import com.feathersoft.counter.core.model.Counter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeActivity : AppCompatActivity() {

  lateinit var countersRecycler: RecyclerView

  val counterAdapter = CounterAdapter()

  var counter: Int = 0

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_home)

    countersRecycler = findViewById(R.id.home_recycler_counters)

    countersRecycler.adapter = counterAdapter
    countersRecycler.layoutManager = LinearLayoutManager(
        this@HomeActivity,
        LinearLayoutManager.VERTICAL,
        false
    )

    findViewById<FloatingActionButton>(R.id.home_button_add).setOnClickListener {
      counterAdapter.addCounter(Counter("Counter # ${++counter}"))
    }
  }
}
