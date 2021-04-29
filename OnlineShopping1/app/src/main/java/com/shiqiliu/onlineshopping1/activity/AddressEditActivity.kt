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
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.shiqiliu.onlineshopping1.R
import com.shiqiliu.onlineshopping1.adapter.AddressAdapter
import com.shiqiliu.onlineshopping1.appurl.Endpoints
import com.shiqiliu.onlineshopping1.modules.Address
import com.shiqiliu.onlineshopping1.modules.AddressResponse
import kotlinx.android.synthetic.main.activity_add_new_address.*
import kotlinx.android.synthetic.main.activity_address_edit.*
import kotlinx.android.synthetic.main.app_bar.*
import org.json.JSONObject

class AddressEditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address_edit)
        init()
    }
    fun setUpToolBar(){
        var toolbar = toolbar
        toolbar.title = "Edit Address"
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
                startActivity(Intent(this,AddressActivity::class.java))
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
        var address = intent.getSerializableExtra("KEY_Address") as Address
        var text_city = address.city
        Log.d("abc","address:${text_city}")
        edit_text_address_city_edit.setText(address.city)
        Log.d("abc","address:${edit_text_address_city_edit.text.toString()}")
        edit_text_address_floor_edit.setText(address.houseNo)
        edit_text_address_pincode_edit.setText((address.pincode).toString())
        edit_text_address_type_edit.setText(address.type)
        edit_text_address_street_name_edit.setText(address.streetName)

        button_save_address_edit.setOnClickListener {
            var streetName = edit_text_address_street_name_edit.text.toString()
            var houseNo = edit_text_address_floor_edit.text.toString()
            var city = edit_text_address_city_edit.text.toString()
            var pincode = edit_text_address_pincode_edit.text.toString()
            var type = edit_text_address_type_edit.text.toString()

            var id = address._id
            var jsonObject = JSONObject()


            jsonObject.put("pincode", pincode)
            jsonObject.put("houseNo", houseNo)
            jsonObject.put("streetName", streetName)
            jsonObject.put("type", type)
            jsonObject.put("city", city)
            //   jsonObject.put("_id",id)
            //     var sharedPreferences = getSharedPreferences("my_pref", Context.MODE_PRIVATE)
            var requestQueue = Volley.newRequestQueue(this)
            var request = JsonObjectRequest(
                Request.Method.PUT,
                Endpoints.updateAddress(id),
                jsonObject,
                Response.Listener {
                    Toast.makeText(applicationContext, "Successful", Toast.LENGTH_SHORT).show()
                    Log.d("abc", "edit")
                    startActivity(Intent(this,AddressActivity::class.java))
                },
                Response.ErrorListener {
                    Toast.makeText(applicationContext, "Fail", Toast.LENGTH_SHORT).show()
                    Log.d("abcd", "$jsonObject")
                    Log.d("abcd", "${Endpoints.updateAddress(id)}")
                }
            )
            requestQueue.add(request)


        }
    }



}