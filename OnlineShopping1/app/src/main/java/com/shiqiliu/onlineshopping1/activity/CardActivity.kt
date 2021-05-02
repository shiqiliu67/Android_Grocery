package com.shiqiliu.onlineshopping1.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.shiqiliu.onlineshopping1.R
import kotlinx.android.synthetic.main.activity_card.*
import kotlinx.android.synthetic.main.app_bar.*

class CardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card)
        init()
    }
    fun setUpToolBar(){
        var toolbar = toolbar
        toolbar.title = "Add Card Information"
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
            R.id.menu_my_account->{
                var intent = Intent(this,MyAccountActivity::class.java)
                startActivity(intent)
                Toast.makeText(applicationContext, "MyAccount", Toast.LENGTH_SHORT).show()
            }
            R.id.menu_return->{
                var intent = Intent(this,PaymentActivity::class.java)
                var address =
                intent.getSerializableExtra("KEY_Address") as com.shiqiliu.onlineshopping1.modules.Address
                Log.d("abc", "address1:$address")
                intent.putExtra("KEY_Address", address)
                startActivity(intent)
                Toast.makeText(applicationContext, "return", Toast.LENGTH_SHORT).show()
            }
            R.id.menu_refresh->{
                var intent = Intent(this,this::class.java)
                startActivity(intent)
                Toast.makeText(applicationContext, "Refresh", Toast.LENGTH_SHORT).show()
            }
        }

        return true
    }


    private fun init() {
        var address =
            intent.getSerializableExtra("KEY_Address") as com.shiqiliu.onlineshopping1.modules.Address
        Log.d("abc", "address1:$address")
       setUpToolBar()
       button_card_back_payment.setOnClickListener {
               Toast.makeText(applicationContext, "Continue Payment", Toast.LENGTH_SHORT).show()
           var intent = Intent(this,PaymentActivity::class.java)
           intent.putExtra("KEY_Address", address)
           startActivity(intent)
       }
        button_card_add_card.setOnClickListener {
            Toast.makeText(applicationContext, "Add Card Information Successful", Toast.LENGTH_SHORT).show()
            var cardNumber = edit_text_card_number.text.toString()
            var cardName = edit_text_name_card.text.toString()
            var cardDate = edit_text_end_date_Card.text.toString()
            var cardCvc = edit_text_cvs_card.text.toString().toInt()
            var zipCode = edit_text_start_date_card.text.toString().toInt()
            var sessionManager = SessionManagerCard(this)
            sessionManager.addCardInfo(cardNumber,cardName,cardDate,cardCvc,zipCode)
            Log.d("abc","$cardNumber,$cardCvc,$cardDate,$cardNumber")

        }
    }
}