package com.feathersoft.counter.home

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.feathersoft.counter.R
import com.feathersoft.counter.core.architecture.BaseActivity
import com.feathersoft.counter.core.model.Counter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class HomeActivity : BaseActivity() {

  private lateinit var countersRecycler: RecyclerView

  private val counterAdapter = CounterAdapter()

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

    counterAdapter.withSelectionListener {
      Snackbar.make(countersRecycler, "Selected counter is ${it.name}", Snackbar.LENGTH_SHORT).show()
    }

    findViewById<FloatingActionButton>(R.id.home_button_add).setOnClickListener {
      counterAdapter.addCounter(Counter("Counter # ${++counter}"))
    }

    handleDisposable(Counter.api(retrofit)
        .all()
        .subscribe(
            { counterAdapter.refreshCounters(it) },
            { Snackbar.make(countersRecycler, "Failed to load: ${it.message}", Snackbar.LENGTH_SHORT).show() }))
  }
}
