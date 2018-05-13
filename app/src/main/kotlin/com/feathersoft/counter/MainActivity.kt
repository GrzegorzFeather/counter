package com.feathersoft.counter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

  lateinit var homeContainer: View

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    homeContainer = findViewById(R.id.home_container)

    findViewById<FloatingActionButton>(R.id.home_button_add).setOnClickListener {
      Snackbar.make(homeContainer, "Add", Snackbar.LENGTH_SHORT).show()
    }
  }
}
