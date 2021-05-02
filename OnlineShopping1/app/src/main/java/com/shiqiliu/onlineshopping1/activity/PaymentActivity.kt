package com.shiqiliu.onlineshopping1.activity

import android.content.Context
import android.content.Intent
import android.location.Address
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.JsonObject
import com.shiqiliu.onlineshopping1.R
import com.shiqiliu.onlineshopping1.appurl.Endpoints
import com.shiqiliu.onlineshopping1.database.DBHelper
import com.shiqiliu.onlineshopping1.modules.OrderSummary
import com.shiqiliu.onlineshopping1.modules.Product
import kotlinx.android.synthetic.main.activity_payment.*
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.row_address_adapter.*
import org.json.JSONArray
import org.json.JSONObject

class PaymentActivity : AppCompatActivity() {
    var click: Boolean = false
    var goTocard: Boolean = true
    lateinit var payment_method: String
    lateinit var dbHelper: DBHelper
    lateinit var mList: List<Product>
    lateinit var orderStatus:String
    lateinit var totalPrice: String
    lateinit var discount: String
    lateinit var toPay: String
    lateinit var delivery: String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)
        init()
    }

    fun setUpToolBar() {
        var toolbar = toolbar
        toolbar.title = "Payment"
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
                var intent = Intent(this, AddressActivity::class.java)
                startActivity(intent)
                Toast.makeText(applicationContext, "return", Toast.LENGTH_SHORT).show()
            }
            R.id.menu_refresh -> {
                var intent = Intent(this, PaymentActivity::class.java)
                startActivity(intent)
                Toast.makeText(applicationContext, "Refresh", Toast.LENGTH_SHORT).show()
            }
            R.id.menu_my_account -> {
                var intent = Intent(this, MyAccountActivity::class.java)
                startActivity(intent)
                Toast.makeText(applicationContext, "MyAccount", Toast.LENGTH_SHORT).show()
            }
        }
        return true
    }


    private fun init() {
        setUpToolBar()

        Log.d("abc", "${text_view_total_payment.text.toString()}")
        var sharedPreferences = getSharedPreferences("my_pref_price", Context.MODE_PRIVATE)
        totalPrice = sharedPreferences.getString("totalPrice", "").toString()
        discount = sharedPreferences.getString("discount", "").toString()
        toPay = sharedPreferences.getString("toPay", "").toString()
        delivery = sharedPreferences.getString("delivery", "").toString()
     //   count = sharedPreferences.getInt("count", 0)
        Log.d("abc", "totalPrice:$totalPrice")

        text_view_total_payment.text = "$$toPay"
        text_view_total_amount_price_payment.text = "$$totalPrice"
        text_view_discount_amount_price_payment.text = "- $$discount"
        text_view_delivery_amount_price_payment.text = "$$delivery"
        text_view_to_pay_amount_price_payment.text = "$$toPay"

        // var orderSummary = OrderSummary()
        var address =
            intent.getSerializableExtra("KEY_Address") as com.shiqiliu.onlineshopping1.modules.Address
        Log.d("abc", "address1:$address")
        //set button click

        radio_button_pay_cash.setOnClickListener {
            orderStatus ="completed"
            payment_method = "cash"
            OrderSummary()
            click = true
        }
        radio_button_pay_online.setOnClickListener {
            orderStatus ="completed"
            payment_method = "online"
            OrderSummary()
            click = true
        }

      //  if(checkClickButton()){
        button_continue_payment.setOnClickListener {
            var intent = Intent(this, CompleteActivity::class.java)
            startActivity(intent)
        }
        //}
//        else{
//            Toast.makeText(applicationContext,"Choose Payment Method First",Toast.LENGTH_SHORT).show()
//        }


        text_view_add_card.setOnClickListener {
            if (goTocard) {
                goTocard = false
                var intent = Intent(this, CardActivity::class.java)
                intent.putExtra("KEY_Address", address)
                startActivity(intent)
                finish()
            }
        }


    }
//checkevennt
    private fun checkClickButton(): Boolean {
        radio_button_pay_cash.setOnClickListener {
            orderStatus ="completed"
            payment_method = "cash"
            OrderSummary()
            click = true
        }
        radio_button_pay_online.setOnClickListener {
            orderStatus ="completed"
            payment_method = "online"
            OrderSummary()
            click = true
        }
        return false
    }

    private fun OrderSummary() {
        //get order details
        var dbHelper = DBHelper(this)
        var mList = dbHelper.getAllProduct()
        //from address activity
        var address =
        intent.getSerializableExtra("KEY_Address") as com.shiqiliu.onlineshopping1.modules.Address
        Log.d("abc", "address:$address")
        //put order summary into orderJsonObject
        var orderJsonObject = JSONObject()
        orderJsonObject.put("orderAmount", toPay)
        orderJsonObject.put("discount", discount)
        orderJsonObject.put("ourPrice", toPay)
        orderJsonObject.put("deliveryCharges", delivery)
        orderJsonObject.put("totalAmount", totalPrice)

        //payment into paymentJsonObject // _id: String, paymentMode paymentStatus
        var paymentJsonObject = JSONObject()
        paymentJsonObject.put("paymentMode", payment_method)
        paymentJsonObject.put("paymentStatus", orderStatus)
        // val _id  city houseNo pincode streetName type
        var addressJsonObject = JSONObject()
        addressJsonObject.put("_id", address._id)
        addressJsonObject.put("city", address.city)
        addressJsonObject.put("houseNo", address.houseNo)
        addressJsonObject.put("pincode", address.pincode)
        addressJsonObject.put("streetName", address.streetName)
        addressJsonObject.put("type", address.type)
        //user
        var sharedPreferences_user = getSharedPreferences("my_pref", Context.MODE_PRIVATE)
        var userJsonObject = JSONObject()
        var id = sharedPreferences_user.getString("id", "")
        var email = sharedPreferences_user.getString("email", "")
        var firstName = sharedPreferences_user.getString("firstName", "")
        var mobile = sharedPreferences_user.getString("mobile", "")
        userJsonObject.put("name", firstName)
        userJsonObject.put("_id", id)
        userJsonObject.put("firstName", firstName)
        userJsonObject.put("mobile", mobile)
        userJsonObject.put("email", email)
        //product
       // var jsonArray: ArrayList<JSONObject> = ArrayList()
        var jsonArray = JSONArray()
        for (product in mList) {
            var productJsonObject = JSONObject()
            productJsonObject.put("quantity", product.quantity)
            productJsonObject.put("mrp", product.mrp)
            productJsonObject.put("productName", product.productName)
            Log.d("abc", "${product.productName}")
            Log.d("abc", "${product.quantity}")
            productJsonObject.put("price", product.price)
            productJsonObject.put("image", product.image)
            jsonArray.put(productJsonObject)
        }
        //Json
        var jsonObject = JSONObject()

        jsonObject.put("orderSummary", orderJsonObject)
        jsonObject.put("payment", paymentJsonObject)
        jsonObject.put("user", userJsonObject)
        jsonObject.put("shippingAddress", addressJsonObject)
        jsonObject.put("products", jsonArray)
        //jsonObject.put("_id", id)
        jsonObject.put("userId", id)

        var requestQueue = Volley.newRequestQueue(this)
        var request = JsonObjectRequest(
            Request.Method.POST,
            Endpoints.postOrders(),
            jsonObject,
            Response.Listener {
                dbHelper.EmptyCart()
                Toast.makeText(applicationContext, "We got the order", Toast.LENGTH_SHORT).show()
            },
            Response.ErrorListener {
                Log.d("abc", "$jsonObject")
                Log.d("abc", "${it.message}")
                Toast.makeText(applicationContext, "failed", Toast.LENGTH_SHORT).show()
              //  Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT).show()
            }
        )
        requestQueue.add(request)

    }


}