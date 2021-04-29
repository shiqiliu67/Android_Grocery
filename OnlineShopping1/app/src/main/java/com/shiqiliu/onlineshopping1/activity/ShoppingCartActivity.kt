package com.shiqiliu.onlineshopping1.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.shiqiliu.onlineshopping1.R
import com.shiqiliu.onlineshopping1.adapter.AdapterShoppingCart
import com.shiqiliu.onlineshopping1.database.DBHelper
import com.shiqiliu.onlineshopping1.modules.Product
import kotlinx.android.synthetic.main.activity_shopping_cart.*
import kotlinx.android.synthetic.main.activity_shopping_cart.view.*

class ShoppingCartActivity : AppCompatActivity() {
    lateinit var dbHelper: DBHelper
    lateinit var mList:List<Product>
    var totalPrice :Float = 0.0F
    var delivery = 10.00
    var discount =0.00
    var toPay:Float = 0.0F
    lateinit var adapterShoppingCart: AdapterShoppingCart
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //set two different layout
        setContentView(R.layout.activity_shopping_cart)
        //in activity_shopping_cart xml we write include to make two layout in the activity
        //now we need to set empty visible
        empty_cart_layout.visibility = View.GONE

        dbHelper = DBHelper(this)
        mList = dbHelper.getAllProduct()
        init()

    }
    @JvmName("getTotalPrice1")
    fun getTotalPrice():Float {
        for(product in mList){
            totalPrice += (product.quantity * product.price!!)

        }
        return totalPrice
    }
    fun checkDelivery(totalPrice:Float):Boolean{
        return totalPrice>=200
    }
    private fun init() {
        checkEmpty()
     //   var product = intent.getSerializableExtra(Product.KEY_Product) as Product
        adapterShoppingCart = AdapterShoppingCart(this)
        recycler_view_cart.adapter = adapterShoppingCart
        recycler_view_cart.layoutManager =LinearLayoutManager(this)

        image_view_return.setOnClickListener {
            var intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
     //calculate the price and payment
        text_view_total_amount_price.text = "$${getTotalPrice().toString()}"//total price
        discount = (totalPrice * 0.1)
        text_view_discount_amount_price.text = "- $$discount"//discount
         when(checkDelivery(totalPrice)){
           true -> {
                        delivery = 0.00
                        text_view_delivery_amount_price.text = "$$delivery"
                    }
           false -> {
                        text_view_delivery_amount_price.text = "$$delivery"
                    }
         }//delivery price>60->free,else $10
        toPay  = (totalPrice+delivery-discount).toFloat()
        text_view_to_pay_amount_price.text = "$$toPay"

    //intent
        button_check_out.setOnClickListener {
            var intent = Intent(this,AddressActivity::class.java)
            startActivity(intent)
        }

            text_view_cart_number.text = mList.size.toString()

        var sessionManager= SessionManager(this)
        sessionManager.addInfo(totalPrice,discount,delivery,toPay)

    }
    //check empty
    private fun checkEmpty(){
         if(dbHelper.checkCartEmpty()){
            shopping_layout.visibility = View.GONE
            empty_cart_layout.visibility = View.VISIBLE
         }
    }
}