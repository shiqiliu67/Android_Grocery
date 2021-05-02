package com.shiqiliu.onlineshopping1.activity

import android.content.Context

class SessionManagerCard (var mContext:Context){
    //card
    private val File_Name_Card ="my_pref_card"
    private val Key_Card_Number="cardNumber"
    private val Key_CardName="name"
    private val Key_Card_date="expiration"
    private val KEY_Card_Cvc="cvc"
    private val KEY_Card_ZipCode="zipcode"

    var sharedPreferences_card = mContext.getSharedPreferences(File_Name_Card, Context.MODE_PRIVATE)
    var editor_card =  sharedPreferences_card.edit()

    fun addCardInfo(cardNumber:String,cardName:String,cardDate:String,cardCVC:Int,zipcode:Int){
        editor_card.putString(Key_Card_Number,cardNumber)
        editor_card.putString(Key_CardName,cardName)
        editor_card.putString(Key_Card_date,cardDate)
        editor_card.putString(KEY_Card_Cvc,cardCVC.toString())
        editor_card.putString(KEY_Card_ZipCode,zipcode.toString())
        editor_card.commit()
    }
}