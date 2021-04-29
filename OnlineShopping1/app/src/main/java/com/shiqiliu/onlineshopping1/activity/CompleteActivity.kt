package com.shiqiliu.onlineshopping1.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shiqiliu.onlineshopping1.R
import kotlinx.android.synthetic.main.activity_complete.*

class CompleteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complete)
        init()
    }

    private fun init() {
        button_back.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}