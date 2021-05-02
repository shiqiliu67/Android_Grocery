package com.shiqiliu.onlineshopping1.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.shiqiliu.onlineshopping1.R
import com.shiqiliu.onlineshopping1.appurl.Config
import com.shiqiliu.onlineshopping1.database.DBHelper
import com.shiqiliu.onlineshopping1.modules.Product
import com.shiqiliu.onlineshopping1.modules.Product.Companion.KEY_Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_product_detail_acivity.*
import kotlinx.android.synthetic.main.app_bar.*

class ProductDetailAcivity : AppCompatActivity() {
    lateinit var dbHelper: DBHelper
    lateinit var product: Product
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail_acivity)
        init()
    }
    fun setUpToolBar(){
        var toolbar = toolbar
        toolbar.title = "Product Details"
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
                var intent = Intent(this,SubCategoryActivity::class.java)
                Log.d("abc","product $product")
                intent.putExtra("Cat_Id",product.catId)
                startActivity(intent)
                Toast.makeText(applicationContext, "return", Toast.LENGTH_SHORT).show()
            }
            R.id.menu_refresh->{
//                var intent = Intent(this,ProductDetailAcivity::class.java)
//                startActivity(intent)
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
        dbHelper = DBHelper(this)
        product = intent.getSerializableExtra(KEY_Product) as Product

        text_view_product_name_detail.text = product.productName
        text_view_product_unit_detail.text = product.unit
        text_view_product_description_detail.text = product.description
        text_view_product_price_detail.text = "$"+product.price.toString()
        //text_view_product_mrp_detail.text = product.mrp.toString()
        Picasso.get()
                .load("${Config.IMAGE_URL+product.image}")
                .into(image_view_detail)

        image_view_detail.setOnClickListener {
            var intent = Intent(this,ImageActivity::class.java)
            intent.putExtra(KEY_Product,product)
            startActivity(intent)
        }

        button_add_detail.setOnClickListener {
            //check item if same
//            if (dbHelper.containProduct(product)){
//                dbHelper.increaseNumber(product)
//            }
            dbHelper.addToCart(product)
            Toast.makeText(applicationContext, "Add to cart Successful", Toast.LENGTH_SHORT).show()
        }

        button_view_chart_detail.setOnClickListener {
            var intent = Intent(this,ShoppingCartActivity::class.java)
            intent.putExtra(KEY_Product,product)
            startActivity(intent)
        }
    }
}