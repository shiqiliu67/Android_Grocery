package com.shiqiliu.onlineshopping1.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.shiqiliu.onlineshopping1.R
import com.shiqiliu.onlineshopping1.adapter.AdapterOrderDetails
import com.shiqiliu.onlineshopping1.adapter.AdapterOrderSummary
import com.shiqiliu.onlineshopping1.modules.Order
import kotlinx.android.synthetic.main.activity_order_details.*
import kotlinx.android.synthetic.main.app_bar.*

class OrderDetailsActivity : AppCompatActivity() {
    var orderList:ArrayList<Order> = ArrayList()
    lateinit var adapterOrderDetails: AdapterOrderDetails
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_details)
  //      init()
    }

    private fun init() {
        setUpToolBar()
        adapterOrderDetails = AdapterOrderDetails(supportFragmentManager)
        view_pager_order.adapter = adapterOrderDetails
        tab_layout.setupWithViewPager(view_pager_order)

        //var id = intent.getStringExtra("KEY_Id")
        //put data in to summary fragment
        var order = intent.getSerializableExtra("KEY_Order") as Order
        adapterOrderDetails.addFragment(order)
    }
    fun setUpToolBar(){
        var toolbar = toolbar
        toolbar.title = "Order Details"
        setSupportActionBar(toolbar)
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
                var intent = Intent(this,OrderDetailsActivity::class.java)
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
}