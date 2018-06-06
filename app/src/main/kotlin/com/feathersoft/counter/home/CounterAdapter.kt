package com.feathersoft.counter.home

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.feathersoft.counter.R
import com.feathersoft.counter.core.model.Counter
import com.feathersoft.counter.core.util.layoutInflater

class CounterAdapter : RecyclerView.Adapter<CounterAdapter.CounterViewHolder>() {

  private val counters: MutableList<Counter> = mutableListOf()

  var selectionListener : ((Counter) -> Unit)? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CounterViewHolder {
    return CounterViewHolder(parent
        .context
        .layoutInflater()
        .inflate(R.layout.item_counter, parent, false))
  }

  override fun getItemCount() = counters.size

  override fun onBindViewHolder(holder: CounterViewHolder, position: Int) {
    val counter = counters[position]
    holder.bind(counter)
    holder.itemView.setOnClickListener({
      selectionListener?.invoke(counter)
    })
  }

  fun refreshCounters(newCounters: List<Counter>) {
    counters.clear();
    counters.addAll(newCounters)
    notifyDataSetChanged()
  }

  fun addCounter(counter: Counter) {
    counters += counter
    notifyItemChanged(counters.size)
  }

  fun withSelectionListener(listener: (Counter) -> Unit) {
    selectionListener = listener
  }

  class CounterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val counterNameTextView: TextView

    init {
      counterNameTextView = itemView.findViewById(R.id.counter_name)
    }

    fun bind(counter: Counter) {
      counterNameTextView.text = "${counter.name} #${counter.id}"
    }
  }
}

