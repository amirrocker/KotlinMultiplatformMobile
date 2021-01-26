package com.pixolus.kmmapplication.KMMAndroidApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pixolus.kmmapplication.shared.Greeting
import android.widget.TextView
import com.pixolus.kmmapplication.KMMAndroidApp.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.text_view)
        tv.text = greet()
    }

    private fun greet(): String {
        return Greeting().greeting()
    }

}
