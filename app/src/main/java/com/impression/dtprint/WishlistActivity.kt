package com.impression.dtprint

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class WishlistActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wishlist)

        title = resources.getString(R.string.your_wishlist)
    }
}
