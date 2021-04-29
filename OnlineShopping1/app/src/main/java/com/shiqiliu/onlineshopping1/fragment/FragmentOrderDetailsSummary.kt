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

class FragmentOrderDetailsSummary : Fragment() {
    var mContext: Context? = null
    var order: Order? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            //param1 = it.getString(ARG_PARAM1)
            //get data
            order = it.getSerializable("KEY_Order") as Order
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_order_summary, container, false)
        text_view_order_date_fragment.text = order?.date!!
        text_view_order_address_fragment.text =  order?.shippingAddress.toString()
        text_view_order_summary.text = order?.orderSummary?.totalAmount.toString()
        text_view_discount_amount_price_payment.text = order?.orderSummary?.discount.toString()
        text_view_delivery_amount_price_payment.text = order?.orderSummary?.deliveryCharges.toString()
        text_view_to_pay_amount_price_payment_fragment.text = order?.orderSummary?.ourPrice.toString()
        return view
    }





companion object {
    @JvmStatic
    fun newInstance(order:Order) =
        FragmentOrderDetailsSummary().apply {
            arguments = Bundle().apply {
                putSerializable("KEY_Order", order)
            }
        }
}
}