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

    //card
    private val File_Name_Card ="my_pref_card"
    private val Key_Card_Number="cardNumber"
    private val Key_CardName="name"
    private val Key_Card_date="expiration"
    private val KEY_Card_Cvc="cvc"
    private val KEY_Card_ZipCode="zipcode"
    var sharedPreferences_card = mContext.getSharedPreferences(File_Name_Card,Context.MODE_PRIVATE)
    var editor_card = sharedPreferences_card.edit()
    fun addCardInfo(cardNumber:String,cardName:String,cardDate:String,cardCVC:Int,zipcode:Int){
        editor_card.putString(Key_Card_Number,cardNumber)
        editor_card.putString(Key_CardName,cardName)
        editor_card.putString(Key_Card_date,cardDate)
        editor_card.putString(KEY_Card_Cvc,cardCVC.toString())
        editor_card.putString(KEY_Card_ZipCode,zipcode.toString())

        editor.commit()
    }
}
