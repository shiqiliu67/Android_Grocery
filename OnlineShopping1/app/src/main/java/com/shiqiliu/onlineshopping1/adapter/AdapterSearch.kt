package com.shiqiliu.onlineshopping1.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shiqiliu.onlineshopping1.R
import com.shiqiliu.onlineshopping1.appurl.Config
import com.shiqiliu.onlineshopping1.modules.Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_search_adapter.view.*

class AdapterSearch (var mContext: Context):
    RecyclerView.Adapter<AdapterSearch.MyViewHolder>(){
    var mList :ArrayList<Product> = ArrayList()
    inner class MyViewHolder(iteView: View) : RecyclerView.ViewHolder(iteView){
        fun bind(product: Product){
            itemView.text_view_product_name_item_search.text = product.productName
            itemView.text_view_product_quantity_item_search.text = product.unit
            itemView.text_view_product_price_item_search.text = product.price.toString()

            Picasso.get().load("${Config.IMAGE_URL+product.image}").into(itemView.image_view_item_search)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view = LayoutInflater.from(mContext).inflate(R.layout.row_search_adapter,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var product = mList[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int {
        return mList.size
    }
    fun setData(list: ArrayList<Product>){
        mList = list
        notifyDataSetChanged()
    }
}