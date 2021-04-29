package com.shiqiliu.onlineshopping1.fragment

import android.content.Context
import android.content.LocusId
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
import com.shiqiliu.onlineshopping1.adapter.AdapterProduct
import com.shiqiliu.onlineshopping1.adapter.AdapterSubFragment
import com.shiqiliu.onlineshopping1.appurl.Endpoints
import com.shiqiliu.onlineshopping1.modules.Category
import com.shiqiliu.onlineshopping1.modules.Product
import com.shiqiliu.onlineshopping1.modules.ProductResponse
import kotlinx.android.synthetic.main.fragment_product_list.*
import kotlinx.android.synthetic.main.fragment_product_list.view.*

class ProductFragment : Fragment() {
    private var mList:ArrayList<Product> = ArrayList()
    var mContext : Context? = null
    var sub_id :Int? = null
    lateinit var adapterProduct: AdapterProduct
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        mContext=context
//    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
           sub_id = it.getInt("KEY_CAT_ID")
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_product_list, container, false)
        getData()
        adapterProduct = AdapterProduct(activity!!)
        view.recycler_view_product.adapter = adapterProduct
        view.recycler_view_product.layoutManager = LinearLayoutManager(activity)
        return view
    }
//q3
    private fun getData() {
        var requestQueue = Volley.newRequestQueue(activity)
        var request = StringRequest(
            Request.Method.GET,
            Endpoints.getProductBySubId(sub_id!!),
            Response.Listener {
                var gson = Gson()
                var productResponse = gson.fromJson(it,ProductResponse::class.java)
                adapterProduct.setData(productResponse.data)
            },
            Response.ErrorListener {
              //  Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT).show()
                Log.d("abc", it.message.toString())
            }
        )
    requestQueue.add(request)
    }


    companion object {
        fun newInstance(catId: Int) =
            ProductFragment().apply {
                arguments = Bundle().apply {
                    putInt("KEY_CAT_ID",catId)
                }
            }
    }
}