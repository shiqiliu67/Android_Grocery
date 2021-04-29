package com.shiqiliu.onlineshopping1.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.shiqiliu.onlineshopping1.R
import com.shiqiliu.onlineshopping1.adapter.AdapterCategory
import com.shiqiliu.onlineshopping1.adapter.AdapterSubFragment
import com.shiqiliu.onlineshopping1.appurl.Endpoints
import com.shiqiliu.onlineshopping1.modules.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_sub_category.*
import kotlinx.android.synthetic.main.app_bar.*
import java.util.*
import kotlin.collections.ArrayList


class SubCategoryActivity : AppCompatActivity() {
    var subCategoryList:ArrayList<SubCategory> = ArrayList()
    lateinit var adapterSubFragment : AdapterSubFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_category)
//        var category = intent.getSerializableExtra(Category.KEY_Category) as Category
//        catId = category.catId
//        Log.d("abc","catid: $catId")
        init()
    }
    fun setUpToolBar(){
        var toolbar = toolbar
        toolbar.title = "Sub_Category"
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
                var intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
                Toast.makeText(applicationContext, "return", Toast.LENGTH_SHORT).show()
            }
            R.id.menu_refresh->{
//                var intent = Intent(this,SubCategoryActivity::class.java)
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
        getData()
        adapterSubFragment = AdapterSubFragment(supportFragmentManager)

        //   var catid = intent.getIntExtra("CAT_ID",0)
//        adapterSubFragment = AdapterSubFragment(supportFragmentManager)
//        for(category in categoryList){
//            adapterSubFragment.addFragment(category)
//        }

    }

    private fun getData() {
        var catid = intent.getIntExtra("Cat_Id",0)
        Log.d("abc", "$catid")
        var requestQueue = Volley.newRequestQueue(this)
        var request = StringRequest(
            Request.Method.GET,
            Endpoints.getSubCategoryByCatId(catid),
            Response.Listener {
                var gson = Gson()
                var subCategoryResponse = gson.fromJson(it, SubCategoryResponse::class.java)
                subCategoryList = subCategoryResponse.data
                Log.d("abd","$subCategoryList")
                for(subCategory in subCategoryList) {
                    adapterSubFragment.addFragment(subCategory)
                }
                view_pager.adapter = adapterSubFragment
                tab_layout.setupWithViewPager(view_pager)
                Log.d("abd", "${subCategoryList}")
            },
            Response.ErrorListener {
                Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT).show()
                Log.d("abc", it.message.toString())
            }
        )
        requestQueue.add(request)

    }


}