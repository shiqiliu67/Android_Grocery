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
import com.shiqiliu.onlineshopping1.adapter.AddressAdapter
import com.shiqiliu.onlineshopping1.appurl.Endpoints
import com.shiqiliu.onlineshopping1.modules.Address
import com.shiqiliu.onlineshopping1.modules.AddressResponse
import com.shiqiliu.onlineshopping1.modules.SubCategoryResponse
import kotlinx.android.synthetic.main.activity_address.*
import kotlinx.android.synthetic.main.activity_sub_category.*
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.row_address_adapter.*

class AddressActivity : AppCompatActivity() {
    lateinit var addressAdapter: AddressAdapter
    var click: Boolean = false
    var addressList: ArrayList<Address> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address)
        init()
    }

    fun setUpToolBar() {
        var toolbar = toolbar
        toolbar.title = "Address"
        setSupportActionBar(toolbar)
        //supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_cart -> {
                var intent = Intent(this, ShoppingCartActivity::class.java)
                startActivity(intent)
                Toast.makeText(applicationContext, "Cart", Toast.LENGTH_SHORT).show()
            }
            R.id.menu_setting -> {
                Toast.makeText(applicationContext, "Settings", Toast.LENGTH_SHORT).show()
            }
            R.id.menu_logout -> {
                var intent = Intent(this, BeginActivity::class.java)
                startActivity(intent)
                Toast.makeText(applicationContext, "Logout", Toast.LENGTH_SHORT).show()
            }
            R.id.menu_return -> {
                var intent = Intent(this, ShoppingCartActivity::class.java)
                startActivity(intent)
                Toast.makeText(applicationContext, "return", Toast.LENGTH_SHORT).show()
            }
            R.id.menu_refresh -> {
                var intent = Intent(this, this::class.java)
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

        //implement adapter
        addressAdapter = AddressAdapter(this)
        recycler_view_address.layoutManager = LinearLayoutManager(this)
        recycler_view_address.adapter = addressAdapter

        //get address data
        getAddressData()


        //click add new address button
        button_add_new_address.setOnClickListener {
            startActivity(Intent(this, AddNewAddress::class.java))
        }
        //button
        button_continue_address.setOnClickListener {
            var intent = Intent(this, PaymentActivity::class.java)
            //get address
            var address = addressAdapter.getAddress()
            Log.d("abc","$address")
            intent.putExtra("KEY_Address", address)
            Log.d("abc","$address")
            startActivity(intent)

        }


    }

    private fun getAddressData() {
        var sharedPreferences = getSharedPreferences("my_pref", Context.MODE_PRIVATE)
        // var userId = getSharedPreferences("KEY_ID", Context.MODE_PRIVATE)
        var userId = sharedPreferences.getString("id", "")
        Log.d("abc", "user_id: $userId")
        var requestQueue = Volley.newRequestQueue(this)
        var request = StringRequest(
            Request.Method.GET,
            Endpoints.getAddress(userId!!),
            Response.Listener {
                var gson = Gson()
                var addressResponse = gson.fromJson(it, AddressResponse::class.java)
                addressList = addressResponse.data
                Log.d("abd", "$addressList")
                addressAdapter.setData(addressList)
            },
            Response.ErrorListener {
                Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT).show()
                Log.d("abc", it.message.toString())
            }
        )
        requestQueue.add(request)
    }

    private fun checkClickButton(): Boolean {
        button_choose_address.setOnClickListener {
            click = true
        }
        return click
    }
}