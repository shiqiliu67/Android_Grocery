package com.shiqiliu.onlineshopping1.modules

import java.io.Serializable

data class ProductResponse(
    val count: Int,
    val data: ArrayList<Product>,
    val error: Boolean
)

data class Product(
    val __v: Int?,
    val _id: String?,
    val catId: Int?,
    val created: String?,
    val description: String?,
    val image: String?,
    val mrp: Float?,
    val position: Int?,
    val price: Float?,
    val productName: String?,
    val quantity: Int?,
    val status: Boolean?,
    val subId: Int?,
    val unit: String?,
    var count:Int
):Serializable{
    companion object{
        const val KEY_Product="product"
    }
}

data class ProductCart(
    val catId: Int,
    val productName: String,
    val price: Float,
    val image: String,
    val unit: Int
)