package com.shiqiliu.onlineshopping1.modules

import java.io.Serializable

data class AddressResponse(
    var data: ArrayList<Address>,
    var error: Boolean,
    var message: String
)

data class Address(
    var __v: Int,
    var _id: String,
    var city: String,
    var houseNo: String,
    var pincode: Int,
    var streetName: String,
    var type: String,
    var userId: String
):Serializable{
    companion object{
        const val KEY_Address="address"
    }
}