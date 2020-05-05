package com.impression.dtprint.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter
import com.impression.dtprint.fragments.LoginFragment
import com.impression.dtprint.fragments.SignUpFragment

class LoginTabAdapter(var fragmentManager: FragmentManager, private var numOfTabs:Int)
    : FragmentPagerAdapter(fragmentManager, numOfTabs) {




    override fun getItem(position: Int): Fragment {
        when(position){
            0 -> return LoginFragment()
            1 -> return SignUpFragment()
            else -> return  Fragment()
        }
    }

    override fun getCount(): Int {
        return numOfTabs
    }

    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE
    }
}
