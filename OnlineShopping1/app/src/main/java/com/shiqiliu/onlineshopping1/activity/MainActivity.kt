package com.shiqiliu.onlineshopping1.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.shiqiliu.onlineshopping1.R
import com.shiqiliu.onlineshopping1.adapter.AdapterCategory
import com.shiqiliu.onlineshopping1.adapter.AdapterMainBanner
import com.shiqiliu.onlineshopping1.appurl.Endpoints
import com.shiqiliu.onlineshopping1.modules.Category
import com.shiqiliu.onlineshopping1.modules.CategoryResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.row_category_adapter.*

class MainActivity : AppCompatActivity() {
    var mList: ArrayList<Category> = ArrayList()
    lateinit var adapterCategory: AdapterCategory
    lateinit var adapterMainBanner: AdapterMainBanner
    var bannerList : ArrayList<String> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }
    fun setUpToolBar(){
        var toolbar = toolbar
        toolbar.title = "Category"
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
        getData()
        adapterCategory = AdapterCategory(this)
        recycler_view_main.adapter = adapterCategory
        recycler_view_main.layoutManager = GridLayoutManager(this, 2)

        adapterMainBanner = AdapterMainBanner(this,bannerList)
        recycler_view_image_view_banner.adapter = adapterMainBanner
        recycler_view_image_view_banner.layoutManager = LinearLayoutManager(this)
        getImageBanner()
//        Picasso.get().load("https://sunrisemarketplace.com/wp-content/uploads/2020/05/SMP-grocery-contest-banner.jpg").
//        into(image_view_banner_main)

//    //intent to 2nd activity
//        image_view_category.setOnClickListener {
//            var intent = Intent(this,SubCategoryActivity::class.java)
//            startActivity(intent)
//        }
    }

    private fun getImageBanner() {
        bannerList.add("https://sunrisemarketplace.com/wp-content/uploads/2020/05/SMP-grocery-contest-banner.jpg")
        bannerList.add("https://viscoz.com/wp-content/uploads/2020/10/Grocery-Banner.png")
        bannerList.add("https://i.pinimg.com/564x/df/c1/f9/dfc1f9ba2734aa94690f009d721440d7.jpg")
        bannerList.add("https://i2.wp.com/ordersells.com/wp-content/uploads/2020/06/Grocery-Banner-New-1200x400-1.jpg?w=1200&ssl=1")
        bannerList.add("https://s3.envato.com/files/273933420/Preview_Set/6.jpg")
        bannerList.add("https://cdn.static-zoutons.com/images/originals/blog/banner10_1601525800.png")
    }

    private fun getData() {
        var requestQueue = Volley.newRequestQueue(this)
        var request = StringRequest(
            Request.Method.GET,
            Endpoints.getCategory(),
            {
                var gson = Gson()
                var categoryResponse = gson.fromJson(it, CategoryResponse::class.java)
                adapterCategory.setData(categoryResponse.data)
                progress_bar.visibility = View.GONE
            },
            {
                Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT).show()
                Log.d("abc", it.message.toString())
            }
        )
        requestQueue.add(request)
    }
}