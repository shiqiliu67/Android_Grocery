package com.shiqiliu.onlineshopping1.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shiqiliu.onlineshopping1.R
import com.shiqiliu.onlineshopping1.activity.SubCategoryActivity
import com.shiqiliu.onlineshopping1.appurl.Config
import com.shiqiliu.onlineshopping1.modules.Category
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_category_adapter.view.*

class AdapterCategory(var mContext: Context) :
    RecyclerView.Adapter<AdapterCategory.MyViewHolder>(){
    var mList: ArrayList<Category> = ArrayList()
    inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(category: Category){
            itemView.text_view_category_name.text = category.catName
            Picasso
                .get()
                .load("${Config.IMAGE_URL+ category.catImage}")
                .into(itemView.image_view_category)

            itemView.setOnClickListener {
                var intent = Intent(mContext, SubCategoryActivity::class.java)
                intent.putExtra("Cat_Id",category.catId)
                mContext.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       var view = LayoutInflater.from(mContext).inflate(R.layout.row_category_adapter,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       var category = mList[position]
        holder.bind(category)
    }

    override fun getItemCount(): Int {
        return mList.size
    }
    fun setData(list: ArrayList<Category>){
        mList = list
        notifyDataSetChanged()
    }
}