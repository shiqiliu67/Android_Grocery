package com.shiqiliu.onlineshopping1.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.shiqiliu.onlineshopping1.R
import com.shiqiliu.onlineshopping1.adapter.AdapterOrderDetailsImage
import com.shiqiliu.onlineshopping1.appurl.Endpoints
import com.shiqiliu.onlineshopping1.modules.Order
import com.shiqiliu.onlineshopping1.modules.OrderResponse
import com.shiqiliu.onlineshopping1.modules.Product
import kotlinx.android.synthetic.main.fragment_order_image.view.*


class FragmentOrderDetailsImage : Fragment() {
    private var mList: ArrayList<Order> = ArrayList()
    var mContext: Context? = null
    var id: String? = null
    lateinit var adapterOrderDetailsImage: AdapterOrderDetailsImage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getString("KEY_ID")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_order_image, container, false)
        getOrderData()
        adapterOrderDetailsImage = AdapterOrderDetailsImage(activity!!)
        view.recycler_view_order_item.adapter = adapterOrderDetailsImage
        view.recycler_view_order_item.layoutManager = LinearLayoutManager(activity)

        return view
    }

    private fun getOrderData() {
        var requestQueue = Volley.newRequestQueue(activity)
        var request = StringRequest(
            Request.Method.GET,
            Endpoints.getOrders(id!!),
            Response.Listener {
                var gson = Gson()
                var orderResponse = gson.fromJson(it, OrderResponse::class.java)
                mList = orderResponse.data as ArrayList<Order>
                Log.d("abd", "$mList")
                adapterOrderDetailsImage.setData(mList)
            },
            Response.ErrorListener {
                Toast.makeText(mContext, "failed", Toast.LENGTH_SHORT).show()
                Log.d("abc", it.message.toString())
            }
        )
        requestQueue.add(request)
    }

    companion object {
        fun newInstance(param1: String, param2: String) =
            FragmentOrderDetailsImage().apply {
                arguments = Bundle().apply {
                    putString("KEY_ID",id)
                    //putString(ARG_PARAM2, param2)
                }
            }
    }
}