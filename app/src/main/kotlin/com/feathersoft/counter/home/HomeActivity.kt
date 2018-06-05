package com.feathersoft.counter.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.feathersoft.counter.R
import com.feathersoft.counter.core.architecture.BaseActivity
import com.feathersoft.counter.core.model.Counter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import io.reactivex.android.schedulers.AndroidSchedulers

class HomeActivity : BaseActivity() {

  private lateinit var toolbar: Toolbar
  private lateinit var countersRecycler: RecyclerView
  private lateinit var refreshLayout: SwipeRefreshLayout

  private val counterAdapter = CounterAdapter()

  var counter: Int = 0

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.activity_home)

    toolbar = findViewById(R.id.home_toolbar)
    countersRecycler = findViewById(R.id.home_recycler_counters)
    refreshLayout = findViewById(R.id.home_refresh)

    setSupportActionBar(toolbar)

    refreshLayout.setOnRefreshListener { load() }

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
      Counter.api(retrofit)
          .create(Counter("New counter"))
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(
              { counterAdapter.addCounter(it) },
              { Snackbar.make(countersRecycler, "Failed to add counter: ${it.message}", Snackbar.LENGTH_SHORT).show() }
          )
    }

    load()
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.home, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    return when (item?.itemId) {
      R.id.action_refresh -> {
        load()
        true
      }
      else -> super.onOptionsItemSelected(item)
    }

  }

  private fun load() {
    handleDisposable(Counter.api(retrofit)
        .all()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            {
              refreshLayout.isRefreshing = false
              counterAdapter.refreshCounters(it)
            },
            { Snackbar.make(countersRecycler, "Failed to load: ${it.message}", Snackbar.LENGTH_SHORT).show() }))
  }
}
