package com.shiqiliu.onlineshopping1.activity

import android.content.Context
import android.content.Intent
import android.location.Address
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.shiqiliu.onlineshopping1.R
import kotlinx.android.synthetic.main.activity_my_account.*
import kotlinx.android.synthetic.main.app_bar.*

class MyAccountActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_account)
        init()
    }
    fun setUpToolBar(){
        var toolbar = toolbar
        toolbar.title = "My Account"
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
                var intent = Intent(this,MyAccountActivity::class.java)
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
        //go to order
        text_view_order_summary.setOnClickListener {
            var intent = Intent(this,OrderSummaryActivity::class.java)
            startActivity(intent)
        }
        //go to address
        text_view_manager_address.setOnClickListener {
            var intent = Intent(this,AddressActivity::class.java)
            startActivity(intent)
        }
        //get user_name and user_id
        var sharedPreferences = getSharedPreferences("my_pref", Context.MODE_PRIVATE)
        var userId = sharedPreferences.getString("id", "")
        var name = sharedPreferences.getString("firstName","")
        text_view_account_name.text = name
        text_view_account_id.text = "user_id: $userId"
        text_view_manager_card.setOnClickListener {
            var intent = Intent(this,ManageCardActivity::class.java)
            startActivity(intent)
        }
    }

}