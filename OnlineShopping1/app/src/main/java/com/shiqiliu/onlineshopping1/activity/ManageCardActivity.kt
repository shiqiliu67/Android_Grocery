package com.shiqiliu.onlineshopping1.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.shiqiliu.onlineshopping1.R
import kotlinx.android.synthetic.main.activity_manage_card.*
import kotlinx.android.synthetic.main.activity_my_account.*
import kotlinx.android.synthetic.main.app_bar.*

class ManageCardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_card)
        init()
    }

    private fun init() {
        setUpToolBar()

        var sessionManager = getSharedPreferences("my_pref_card",Context.MODE_PRIVATE)
        var cardNumber = sessionManager.getString("cardNumber","")
        var cardDate =  sessionManager.getString("expiration","")
        var cardName =sessionManager.getString("expiration","name")
        Log.d("abc","$cardNumber,$cardDate,$cardName")
        text_view_card_number_manager.text = cardNumber
        text_view_manager_date.text = cardDate
        text_view_manager_name.text = cardName

        text_view_change_card.setOnClickListener {
            var intent = Intent(this,CardActivity::class.java)
            startActivity(intent)
        }


    }
    fun setUpToolBar(){
        var toolbar = toolbar
        toolbar.title = "My Card"
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
                var intent = Intent(this,PaymentActivity::class.java)
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