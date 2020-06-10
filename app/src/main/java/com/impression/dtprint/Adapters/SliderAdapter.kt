package com.impression.dtprint.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.impression.dtprint.R
import com.impression.dtprint.SliderItem
import com.makeramen.roundedimageview.RoundedImageView
import kotlinx.android.synthetic.main.slide_item_container.view.*

class SliderAdapter(S:List<SliderItem>,V:ViewPager2) : RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {
    private  var sliderItem:List<SliderItem> = S
    private var viewPager2: ViewPager2 = V;
    class SliderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private  var imageView:RoundedImageView=itemView.findViewById(R.id.imageSlide);
        fun setImage(sliderItem: SliderItem) {
            imageView.setImageResource(sliderItem.image);
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
    return SliderViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.slide_item_container,parent,false)
    )
    }

    override fun getItemCount(): Int {
        return sliderItem.size
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
    holder.setImage(sliderItem.get(position))
    }


}
