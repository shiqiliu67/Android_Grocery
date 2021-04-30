package com.shiqiliu.onlineshopping1.adapter
import java.io.Serializable
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shiqiliu.onlineshopping1.R
import com.shiqiliu.onlineshopping1.activity.OrderDetailsActivity
import com.shiqiliu.onlineshopping1.modules.Order
import kotlinx.android.synthetic.main.row_orders_summary_adapter.view.*
import java.text.ParseException
import java.text.SimpleDateFormat

class AdapterOrderSummary(var mContext: Context) :
    RecyclerView.Adapter<AdapterOrderSummary.myViewHolder>() {
    var mList: ArrayList<Order> = ArrayList()

    inner class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(order: Order) {
            itemView.text_view_order_date.text = convertMongoDate(order.date)
            itemView.text_view_order_amount.text = "$${order.orderSummary.orderAmount}"
            //for-loop
            var productNameTotal: String = ""
            for (product1 in order.products) {
                // productNameTotal = productNameTotal+product1.productName +" "
              productNameTotal += " ${product1.quantity} x ${product1.productName}\n"
                Log.d("abc", "product name list is $productNameTotal")
            }
            itemView.text_view_order_item.text = productNameTotal
            // itemView.text_view_order_status.text = order.orderStatus
            Log.d("abc", "${order.orderStatus}")//status is null don't know why

            itemView.setOnClickListener {
                var intent = Intent(mContext, OrderDetailsActivity::class.java)
                intent.putExtra("KEY_Order", order)
                //intent.putExtra("KEY_Id", order._id)
                Log.d("abc", "Order is $order")
                mContext.startActivity(intent)

            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        var view = LayoutInflater.from(mContext)
            .inflate(R.layout.row_orders_summary_adapter, parent, false)
        return myViewHolder(view)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
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
    fun convertMongoDate(date: String): String? {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val outputFormat = SimpleDateFormat("MMM d, yyyy")
        try {
            val finalStr: String = outputFormat.format(inputFormat.parse(date))
            println(finalStr)
            return finalStr
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return ""
    }

}