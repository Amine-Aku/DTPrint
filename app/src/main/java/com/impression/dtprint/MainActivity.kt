package com.impression.dtprint

import android.content.ClipData
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.view.menu.MenuView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.impression.dtprint.fragments.*
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_signup.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var drawer: DrawerLayout? = null
    var toolbar: Toolbar? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Realm.init(this)


        //Set Toolbar as the Action Bar
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        //Set The Drawer
        drawer = findViewById(R.id.main_layout)
        var navigationView: NavigationView = findViewById(R.id.draw_nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        var toggle = ActionBarDrawerToggle(
            this, drawer, toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )

        drawer!!.addDrawerListener(toggle)
        toggle.syncState()

        //Set Default Fragment
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment()).commit()
            navigationView.setCheckedItem(R.id.draw_nav_home)
        }


    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.draw_nav_home -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, HomeFragment()).commit()
                toolbar!!.title =  resources.getString(R.string.app_name)
            }

            R.id.draw_nav_gallery -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, GalleryFragment()).commit()
                toolbar!!.title = resources.getString(R.string.draw_nav_gallery)
            }

            R.id.draw_nav_profile -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, ProfileFragment()).commit()
                toolbar!!.title = resources.getString(R.string.draw_nav_profile)
            }

            R.id.draw_nav_orders -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, OrdersFragment()).commit()
                toolbar!!.title = resources.getString(R.string.draw_nav_orders)
            }

            R.id.draw_nav_wishlist -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, WishlistFragment()).commit()
                toolbar!!.title = resources.getString(R.string.draw_nav_wishlist)
            }

            R.id.draw_nav_clients -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, ClientsFragment()).commit()
                toolbar!!.title = resources.getString(R.string.draw_nav_clients)
            }

            R.id.draw_nav_login -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, LoginFragment()).commit()
                toolbar!!.title = resources.getString(R.string.draw_nav_login)
            }

            R.id.draw_nav_signUp -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, SignUpFragment()).commit()
                toolbar!!.title = resources.getString(R.string.draw_nav_signUp)

            }
        }
        drawer!!.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        // If the drawer is open then the Back Button will close it (instead of closing the entire activity as default)
        if (drawer!!.isDrawerOpen(GravityCompat.START)) {
            drawer!!.closeDrawer(GravityCompat.START)
        }
        // Close the activity as default
        else {
            super.onBackPressed()
        }
    }
}
