package com.shiqiliu.onlineshopping1.activity

import android.content.Context
import org.json.JSONObject

class SessionManager (var mContext: Context){
    //intent1.putExtra("Total price",totalPrice)
    //        intent1.putExtra("discount",discount)
//        intent1.putExtra("delivery",delivery)
//        intent1.putExtra("to_pay",toPay)
    private val File_Name ="my_pref_price"
    private val Key_Total="totalPrice"
    private val Key_Discount="discount"
    private val Key_Delivery="delivery"
    private val KEY_ToPay="toPay"

    var sharedPreferences = mContext.getSharedPreferences(File_Name,Context.MODE_PRIVATE)
    var editor = sharedPreferences.edit()
    //user:JSONObject,it:JSONObject
    fun addInfo(totalPrice:Float,discount:Double,delivery:Double,toPay:Float){
        editor.putString(Key_Total,totalPrice.toString())
        editor.putString(Key_Discount,discount.toString())
        editor.putString(Key_Delivery,delivery.toString())
        editor.putString(KEY_ToPay,toPay.toString())
        editor.commit()
    }
//    fun isLoggedIn(): Boolean{
//        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false)
//    }
//    fun login( email:String, password:String):Boolean{
//        var saveEmail = sharedPreferences.getString(Key_Email,null)
//        var savePassword = sharedPreferences.getString(Key_Password,null)
//        return email.equals(saveEmail) && password.equals(savePassword)
//    }
//    fun logout(){
//        editor.clear()
//        editor.commit()
//    }
    fun getPayment(): String? {
       return sharedPreferences.getString("Key_Total","")
    }
    fun getDiscount(): String? {
        return sharedPreferences.getString("Key_Discount","")
    }
    fun getDelivery(): String? {
        return sharedPreferences.getString("Key_Delivery","")
    }
    fun getToPay(): String? {
        return sharedPreferences.getString("KEY_ToPay","")
    }
}
