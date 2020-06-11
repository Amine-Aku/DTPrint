package com.impression.dtprint.fragments

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.impression.dtprint.*
import com.impression.dtprint.Adapters.DocumentsAdapter
import com.impression.dtprint.Adapters.GalleryAdapter
import com.impression.dtprint.Adapters.SliderAdapter
import com.impression.dtprint.dao.ConnectionDB
import com.impression.dtprint.models.Client
import com.impression.dtprint.models.CurrentClient
import com.impression.dtprint.models.Documents
import com.impression.dtprint.models.Produits
import kotlinx.coroutines.Runnable
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.abs

class HomeFragment : Fragment() {

    private val PICK_IMAGE_REQUEST = 1
    var agentBtn: Button? = null
    var livreurBtn: Button? = null
    private lateinit var viewPager2: ViewPager2
    private  val sliderHandler:Handler = Handler()
    private var sliderRunnable =
        java.lang.Runnable {
            viewPager2.currentItem=viewPager2.currentItem+1


        }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_home, container, false)
        viewPager2=v.findViewById(R.id.viewPagerImageSlider)

        //list of images
        var sliderItems: ArrayList<SliderItem> = ArrayList<SliderItem>()
        sliderItems.add(SliderItem(R.drawable.globale))
        sliderItems.add(SliderItem(R.drawable.p10))
        sliderItems.add(SliderItem(R.drawable.p20))
        sliderItems.add(SliderItem(R.drawable.p21))
        sliderItems.add(SliderItem(R.drawable.ic_logo))
        sliderItems.add(SliderItem(R.drawable.p22))
        sliderItems.add(SliderItem(R.drawable.p23))
        sliderItems.add(SliderItem(R.drawable.p24))
        sliderItems.add(SliderItem(R.drawable.p25))

        viewPager2.adapter = SliderAdapter(sliderItems,viewPager2)
        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.offscreenPageLimit=3
        viewPager2.getChildAt(0).overScrollMode =RecyclerView.OVER_SCROLL_NEVER

        var compositePageTransformer:CompositePageTransformer= CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        compositePageTransformer.addTransformer { page, position ->
            val r:Float=1- abs(position)
            page.scaleY =0.85f +r*0.15f
        }
        viewPager2.setPageTransformer(compositePageTransformer)

        viewPager2.registerOnPageChangeCallback(object:ViewPager2.OnPageChangeCallback()
        {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                sliderHandler.removeCallbacks(sliderRunnable)
                sliderHandler.postDelayed(sliderRunnable,2000)


            }
        })





//        agentBtn = v.findViewById(R.id.goto_agent_orders)
//        agentBtn!!.setOnClickListener {
//            val intent = Intent(context, OrdersActivity::class.java)
//            intent.putExtra("user", Client.UserType.Agent.toString())
//            startActivity(intent)
//        }
//
//        livreurBtn = v.findViewById(R.id.goto_livreur_orders)
//        livreurBtn!!.setOnClickListener {
//            val intent = Intent(context, OrdersActivity::class.java)
//            intent.putExtra("user", Client.UserType.Livreur.toString())
//            startActivity(intent)
//        }

        return v
    }


    override fun onPause() {
        super.onPause()
        sliderHandler.removeCallbacks(sliderRunnable)
    }



    override fun onResume() {
        super.onResume()
        sliderHandler.postDelayed(sliderRunnable,2000)

    }
}
/*
*
* */
