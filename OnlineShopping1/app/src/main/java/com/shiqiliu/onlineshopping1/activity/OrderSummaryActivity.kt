package com.shiqiliu.onlineshopping1.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.shiqiliu.onlineshopping1.R
import com.shiqiliu.onlineshopping1.adapter.AdapterOrderSummary
import com.shiqiliu.onlineshopping1.appurl.Endpoints
import com.shiqiliu.onlineshopping1.modules.Address
import com.shiqiliu.onlineshopping1.modules.AddressResponse
import com.shiqiliu.onlineshopping1.modules.Order
import com.shiqiliu.onlineshopping1.modules.OrderResponse
import kotlinx.android.synthetic.main.activity_order_summary.*
import kotlinx.android.synthetic.main.app_bar.*

class OrderSummaryActivity : AppCompatActivity() {
    lateinit var adapterOrderSummary: AdapterOrderSummary
    var orderList: ArrayList<Order> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_summary)
        init()
    }
    fun setUpToolBar(){
        var toolbar = toolbar
        toolbar.title = "Order Summary"
        setSupportActionBar(toolbar)
        //supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_cart -> {
                var intent = Intent(this,ShoppingCartActivity::class.java)
                startActivity(intent)
                Toast.makeText(applicationContext, "Cart", Toast.LENGTH_SHORT).show()}
            R.id.menu_setting ->{ Toast.makeText(applicationContext, "Settings", Toast.LENGTH_SHORT).show()}
            R.id.menu_logout -> {
                var intent = Intent(this,BeginActivity::class.java)
                startActivity(intent)
                Toast.makeText(applicationContext, "Logout", Toast.LENGTH_SHORT).show()}
            R.id.menu_return->{
                var intent = Intent(this,AddressActivity::class.java)
                startActivity(intent)
                Toast.makeText(applicationContext, "return", Toast.LENGTH_SHORT).show()
            }
            R.id.menu_refresh->{
                var intent = Intent(this,PaymentActivity::class.java)
                startActivity(intent)
                Toast.makeText(applicationContext, "Refresh", Toast.LENGTH_SHORT).show()
            }
            R.id.menu_my_account->{
                var intent = Intent(this,MyAccountActivity::class.java)
                startActivity(intent)
                Toast.makeText(applicationContext, "MyAccount", Toast.LENGTH_SHORT).show()
            }
        }
        return true
    }

    private fun init() {
        setUpToolBar()
        //init adapter
        adapterOrderSummary = AdapterOrderSummary(this)
        recycler_view_orders.adapter = adapterOrderSummary
        recycler_view_orders.layoutManager = LinearLayoutManager(this)

        //find data
        getOrderData()

    }

    private fun getOrderData() {
        var sharedPreferences = getSharedPreferences("my_pref", Context.MODE_PRIVATE)
        var userId = sharedPreferences.getString("id", "")
        Log.d("abc", "user_id: $userId")
        var requestQueue = Volley.newRequestQueue(this)
        var request = StringRequest(
            Request.Method.GET,
            Endpoints.getOrders(userId!!),
            Response.Listener {
                var gson = Gson()
                var orderResponse = gson.fromJson(it, OrderResponse::class.java)
                orderList = orderResponse.data as ArrayList<Order>
                Log.d("abd", "$orderList")
               adapterOrderSummary.setData(orderList)
            },
            Response.ErrorListener {
                Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT).show()
                Log.d("abc", it.message.toString())
            }
        )
        requestQueue.add(request)
    }
    }
