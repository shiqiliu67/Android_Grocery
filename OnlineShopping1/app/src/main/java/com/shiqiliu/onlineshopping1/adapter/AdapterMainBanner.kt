package com.shiqiliu.onlineshopping1.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shiqiliu.onlineshopping1.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.rows_banner_adapter.view.*

class AdapterMainBanner (var mContext: Context,var mList :ArrayList<String>):
    RecyclerView.Adapter<AdapterMainBanner.MyViewHolder>(){
    //lateinit var mList: ArrayList<String>
    inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(url:String){
            Picasso.get().load(url).
            into(itemView.image_view_banner_main)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view = LayoutInflater.from(mContext).inflate(R.layout.rows_banner_adapter,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       var url = mList[position]
        return holder.bind(url)
    }

    override fun getItemCount(): Int {
       return mList.size
    }


}