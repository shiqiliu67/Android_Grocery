package com.shiqiliu.onlineshopping1.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shiqiliu.onlineshopping1.R
import com.shiqiliu.onlineshopping1.appurl.Config
import com.shiqiliu.onlineshopping1.modules.Product
import com.shiqiliu.onlineshopping1.modules.Product.Companion.KEY_Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_image.*

class ImageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)
        init()
    }

    private fun init() {
        var product = intent.getSerializableExtra(KEY_Product) as Product
        Picasso.get()
            .load("${Config.IMAGE_URL+product.image}")
            .into(image_view_image_activity)
    }
}