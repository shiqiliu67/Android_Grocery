package com.shiqiliu.onlineshopping1.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shiqiliu.onlineshopping1.R
import com.shiqiliu.onlineshopping1.modules.Order
import com.shiqiliu.onlineshopping1.modules.Product
import kotlinx.android.synthetic.main.row_orders_summary_adapter.view.*

class AdapterOrderSummary(var mContext : Context):RecyclerView.Adapter<AdapterOrderSummary.myViewHolder>(){
    var mList :ArrayList<Order> = ArrayList()
    inner class myViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(order:Order){
            itemView.text_view_order_date.text = order.date
            if(order.orderSummary!=null)
            {itemView.text_view_order_amount.text = order.orderSummary.orderAmount.toString()}
            //for-loop
            var productNameTotal :String=""
            for(i in order.products){
                if(i != null)
              productNameTotal += i.productName +" "
            }
            itemView.text_view_order_item.text = productNameTotal
            itemView.text_view_order_status.text = order.orderStatus
            Log.d("abc","${order.orderStatus}")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
       var view = LayoutInflater.from(mContext).inflate(R.layout.row_orders_summary_adapter,parent,false)
        return myViewHolder(view)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
      var order = mList[position]
        holder.bind(order)
    }

    override fun getItemCount(): Int {
       return mList.size
    }

    fun setData(list: ArrayList<Order>){
        mList = list
        notifyDataSetChanged()
    }

}