package com.shiqiliu.onlineshopping1.modules

import java.io.Serializable

data class OrderResponse(
    val count: Int,
    val data: ArrayList<Order>,
    val error: Boolean
): Serializable

data class Order(
    val __v: Int,
    val _id: String,
    val date: String,
    val orderStatus: String,
    val orderSummary: OrderSummary,
    val payment: Payment,
    val products: List<ProductData>,
    val shippingAddress: ShippingAddress,
    val user: User,
    val userId: String
): Serializable

data class OrderSummary(
    val deliveryCharges: Float,
    val discount: Float,
    val orderAmount: Float,
    val ourPrice: Float,
    val totalAmount: Float
): Serializable

data class Payment(
   // val _id: String,
    val paymentMode: String,
    val paymentStatus: String
): Serializable

data class ShippingAddress(
    val _id: String,
    val city: String,
    val houseNo: String,
    val pincode: Int,
    val streetName: String,
    val type: String
): Serializable

data class ProductData(
    var _id: String,
    var image: String,
    var price: Int,
    var productName: String,
    var quantity :Int
): Serializable