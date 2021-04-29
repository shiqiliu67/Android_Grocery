package com.shiqiliu.onlineshopping1.activity

import android.content.Context
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
import com.shiqiliu.onlineshopping1.modules.User
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {
    lateinit var user: User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        init()
    }

    private fun init() {
        text_view_login_clicktext_jump.setOnClickListener {
            var intent = Intent(this,RegsiterActivity::class.java)
            startActivity(intent)
        }
        button_login.setOnClickListener {
            var sharedPreferences = getSharedPreferences("my_pref", Context.MODE_PRIVATE)
            var editor = sharedPreferences.edit()

            var email_input = edit_text_login_email.text.toString()
            var password_input =edit_text_login_password.text.toString()

            var jsonObject = JSONObject()
            jsonObject.put("email", email_input)
            jsonObject.put("password", password_input)

            var requestQueue = Volley.newRequestQueue(this)
            var jsonRequest = JsonObjectRequest(
                Request.Method.POST,
                Endpoints.getLogin(),
                jsonObject,
                Response.Listener {
                    Toast.makeText(applicationContext, "Welcome", Toast.LENGTH_SHORT).show()
                    Log.d("abc", it.toString())
                    editor.putString("token",it.getString("token"))
                    editor.putString("email",it.getJSONObject("user").getString("email"))
                    editor.putString("id",it.getJSONObject("user").getString("_id"))
                    editor.putString("firstName",it.getJSONObject("user").getString("firstName"))
                    editor.putString("mobile",it.getJSONObject("user").getString("mobile"))
                    editor.putBoolean("isLoggedIn",true)
                    editor.commit()
//                    var sessionManager = SessionManager(this)
//                    sessionManager.addInfo(it.getJSONObject("user"),it)
                    startActivity(Intent(this, MainActivity::class.java))
                },
                Response.ErrorListener {
                 Toast.makeText(applicationContext, "Invalid Email or Password", Toast.LENGTH_SHORT).show()
                }
            )
            requestQueue.add(jsonRequest)

//            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}