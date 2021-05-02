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


}
