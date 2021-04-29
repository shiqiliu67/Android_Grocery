package com.shiqiliu.onlineshopping1.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shiqiliu.onlineshopping1.R
import kotlinx.android.synthetic.main.activity_begin.*

class BeginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_begin)

        init()
    }

    private fun init() {
        button_register_main.setOnClickListener {
            startActivity(Intent(this, RegsiterActivity::class.java))
        }
        button_login_main.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}