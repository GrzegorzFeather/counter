package com.feathersoft.counter.home

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.feathersoft.counter.R
import com.feathersoft.counter.core.model.Counter
import com.feathersoft.counter.core.util.layoutInflater

class CounterAdapter : RecyclerView.Adapter<CounterAdapter.CounterViewHolder>() {

  val counters: MutableList<Counter> = mutableListOf()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CounterViewHolder {
    return CounterViewHolder(parent
        .context
        .layoutInflater()
        .inflate(R.layout.item_counter, parent, false))
  }

  override fun getItemCount() = counters.size

  override fun onBindViewHolder(holder: CounterViewHolder, position: Int) {
    holder.bind(counters[position])
  }

  fun addCounter(counter: Counter) {
    counters += counter
    notifyItemChanged(counters.size)
  }

  class CounterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val counterNameTextView: TextView

    init {
      counterNameTextView = itemView.findViewById(R.id.counter_name)
    }

    fun bind(counter: Counter) {
      counterNameTextView.text = counter.name
    }
  }
}

