package com.shiqiliu.onlineshopping1.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.shiqiliu.onlineshopping1.R
import com.shiqiliu.onlineshopping1.appurl.Endpoints
import kotlinx.android.synthetic.main.activity_add_new_address.*
import kotlinx.android.synthetic.main.app_bar.*
import org.json.JSONObject

class AddNewAddress : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_address)
        init()
    }
    fun setUpToolBar(){
        var toolbar = toolbar
        toolbar.title = "Add New Address"
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
                var intent = Intent(this,this::class.java)
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
        button_save_address.setOnClickListener {
            var sharedPreferences = getSharedPreferences("my_pref", Context.MODE_PRIVATE)
           // var editor = sharedPreferences.edit()

          //  var userId = edit_text_address_name.text.toString()
           // var userId = getSharedPreferences("id",Context.MODE_PRIVATE)
            var userId = sharedPreferences.getString("id", "")
            Log.d("abc","userid:$userId")
            var streetName = edit_text_address_street_name.text.toString()
            var houseNo = edit_text_address_floor.text.toString()
            var city = edit_text_address_city.text.toString()
            var pincode = edit_text_address_pincode.text.toString()
            var type = edit_text_address_type.text.toString()

            var requestQueue = Volley.newRequestQueue(this)
            var jsonObject = JSONObject()
            jsonObject.put("pincode", pincode)
            jsonObject.put("houseNo", houseNo)
            jsonObject.put("streetName", streetName)
            jsonObject.put("type", type)
            jsonObject.put("userId", userId)
            jsonObject.put("city", city) //type: home/office ->int value

            var jsonRequest = JsonObjectRequest(
                Request.Method.POST,
                Endpoints.postAddress(),
                jsonObject,
                Response.Listener {
                    Toast.makeText(applicationContext, "Successful", Toast.LENGTH_SHORT).show()
                    Log.d("abc", it.toString())
                    startActivity(Intent(this, AddressActivity::class.java))
                },
                Response.ErrorListener {
                    Toast.makeText(applicationContext, "Address Already Exist", Toast.LENGTH_SHORT).show()
                }
            )
            requestQueue.add(jsonRequest)
        }
    }
}