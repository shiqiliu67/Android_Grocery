package com.shiqiliu.onlineshopping1.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.shiqiliu.onlineshopping1.R
import com.shiqiliu.onlineshopping1.appurl.Endpoints
import kotlinx.android.synthetic.main.activity_regsiter.*
import org.json.JSONObject

class RegsiterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regsiter)
        init()
    }

    private fun init() {
     button_register.setOnClickListener {
        var name = edit_text_register_name.text.toString()
        var email = edit_text_register_email.text.toString()
        var password = edit_text_register_password.text.toString()
        var phoneNumber = edit_text_register_phone_number.text.toString()

        var requestQueue = Volley.newRequestQueue(this)
        var jsonObject = JSONObject()
        jsonObject.put("firstName", name)
        jsonObject.put("email", email)
        jsonObject.put("password", password)
        jsonObject.put("mobile", phoneNumber)

        var jsonRequest = JsonObjectRequest(
            Request.Method.POST,
            Endpoints.getRegister(),
            jsonObject,
            Response.Listener {
                Toast.makeText(applicationContext, "Successful", Toast.LENGTH_SHORT).show()
                Log.d("abc", it.toString())
                startActivity(Intent(this, LoginActivity::class.java))
            },
            Response.ErrorListener {
              Toast.makeText(applicationContext, "Email Already Exist", Toast.LENGTH_SHORT).show()
            }
        )
        requestQueue.add(jsonRequest)
       //  startActivity(Intent(this, LoginActivity::class.java))
    }
        text_view_register_clicktext_jump.setOnClickListener {
           startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}