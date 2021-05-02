package com.shiqiliu.onlineshopping1.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.shiqiliu.onlineshopping1.R
import com.shiqiliu.onlineshopping1.appurl.Endpoints
import com.shiqiliu.onlineshopping1.modules.Order
import com.shiqiliu.onlineshopping1.modules.OrderResponse
import com.shiqiliu.onlineshopping1.modules.Product
import com.shiqiliu.onlineshopping1.modules.ProductResponse
import kotlinx.android.synthetic.main.fragment_order_summary.*
import kotlinx.android.synthetic.main.fragment_order_summary.view.*
import java.text.ParseException
import java.text.SimpleDateFormat

class FragmentOrderDetailsSummary : Fragment() {
    var mContext: Context? = null
    var order: Order? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            order = it.getSerializable("KEY_Order") as Order
            Log.d("abc","Fragment order 2 $order")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.d("abc","Fragment order 3 $order")
        var view = inflater.inflate(R.layout.fragment_order_summary, container, false)
        //init(view)
        Log.d("abc","Fragment order 4 $order")
        //text_view_order_date_fragment
        view.text_view_order_date_fragment.text = convertMongoDate(order!!.date)
        Log.d("abc","Fragment order 5 $order")
        view.text_view_order_address_fragment.text =  getAddressInfo(order!!)
        view.text_view_total_amount_price_payment.text = "$${order!!.orderSummary.totalAmount.toString()}"
        view.text_view_discount_amount_price_payment.text = "-$${order!!.orderSummary.discount.toString()}"
        view.text_view_delivery_amount_price_payment.text = "$${order!!.orderSummary.deliveryCharges.toString()}"
        view.text_view_to_pay_amount_price_payment_fragment.text = "$${order!!.orderSummary.ourPrice.toString()}"
        Log.d("abc","product are ${order!!.products}")
        return view
    }


    fun getAddressInfo(order:Order):String{
        return "Street Name: ${order.shippingAddress.streetName}\nHouse No: ${order.shippingAddress.houseNo}\n" +
                "City: ${order.shippingAddress.city}\nPincode: ${order.shippingAddress.pincode}\n" +
                "Type: ${order.shippingAddress.type}  "
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

    companion object {
    @JvmStatic
    fun newInstance(order:Order) =
        FragmentOrderDetailsSummary().apply {
            arguments = Bundle().apply {
                putSerializable("KEY_Order", order)
                Log.d("abc","Fragment order $order")
            }
        }
}

}