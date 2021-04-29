package com.shiqiliu.onlineshopping1.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shiqiliu.onlineshopping1.R
import com.shiqiliu.onlineshopping1.modules.Order
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_orders_image_adapter.view.*

class AdapterOrderDetailsImage (var mContext: Context):
    RecyclerView.Adapter<AdapterOrderDetailsImage.MyViewHolder>(){
    var mList :ArrayList<Order> = ArrayList()
    inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(order: Order){
            for (product in order.products) {
                itemView.text_view_order_product_name_fragment.text = product.productName
                itemView.text_view_order_product_price_fragment.text = product.price.toString()
                itemView.text_view_order_product_quantity_fragment.text = product.quantity.toString()
                Log.d("abc", "product +${product.productName} ")

                Picasso.get().load(product.image).into(itemView.image_view_order_fragment)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       var view = LayoutInflater.from(mContext).inflate(R.layout.row_orders_image_adapter,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var order = mList[position]
        holder.bind(order)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    fun setData(list: ArrayList<Order>) {
        mList = list
        notifyDataSetChanged()
    }
}