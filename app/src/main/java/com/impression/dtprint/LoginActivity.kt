package com.impression.dtprint

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout
import com.impression.dtprint.Adapters.LoginTabAdapter

class LoginActivity : AppCompatActivity() {

    private var tabLayout: TabLayout? = null
    private var loginTab: TabItem? = null
    private var signUpTab: TabItem? = null
     var viewPager: ViewPager? = null

    var pageAdapter: PagerAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)



        tabLayout = findViewById(R.id.tab_layout_login)
        loginTab = findViewById(R.id.tab_login)
        signUpTab = findViewById(R.id.tab_signup)
        viewPager = findViewById(R.id.view_pager_login)

        pageAdapter = LoginTabAdapter(supportFragmentManager, tabLayout!!.tabCount)
        viewPager!!.adapter = pageAdapter

        tabLayout!!.setOnTabSelectedListener( object: TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                viewPager!!.currentItem = p0!!.position
                when(p0!!.position){
                    0 -> pageAdapter!!.notifyDataSetChanged()
                    1 -> pageAdapter!!.notifyDataSetChanged()
                }
            }

        })


        viewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

}
