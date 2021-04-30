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
    var mContext: Context? = null
    var products: ArrayList<Product>? = null
    var order: Order? = null
    lateinit var adapterOrderDetailsImage: AdapterOrderDetailsImage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
           // products = it.getSerializable("KEY_Order") as ArrayList<Product>
            order = it.getSerializable("KEY_Order") as Order
            Log.d("abc","Fragment order 2 $order")
            products = order!!.products
            Log.d("abc","2 Fragment product are $products")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_order_image, container, false)
        adapterOrderDetailsImage = AdapterOrderDetailsImage(activity!!)
        view.recycler_view_order_item.adapter = adapterOrderDetailsImage
        view.recycler_view_order_item.layoutManager = LinearLayoutManager(activity)

        init()
        return view
    }

    private fun init() {
        adapterOrderDetailsImage.setData(products!!)
    }

    companion object {
        @JvmStatic
        fun newInstance(order:Order) =
            FragmentOrderDetailsImage().apply {
                arguments = Bundle().apply {
                    putSerializable("KEY_Order", order)
                    Log.d("abc","Fragment order $order")
                }
            }
    }

//    companion object {
//        fun newInstance(products: ArrayList<Product>) =
//            FragmentOrderDetailsImage().apply {
//                arguments = Bundle().apply {
//                    putSerializable("KEY_Order", products)
//                    Log.d("abc","1 Fragment product are $products")
//                }
//            }
//    }
}