package com.impression.dtprint

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class OrdersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orders)

        title = resources.getString((R.string.your_orders))
    }
}
