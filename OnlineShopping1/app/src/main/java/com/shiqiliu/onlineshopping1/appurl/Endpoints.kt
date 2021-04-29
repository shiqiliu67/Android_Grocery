package com.shiqiliu.onlineshopping1.appurl

class Endpoints {

    companion object{

        private const val URL_CATEGORY ="category"
        private const val URL_SUB_CATEGORY = "subcategory/"
        private const val URL_PRODUCT_BY_SUB_ID = "products/sub/"
        private const val URL_REGISTER = "auth/register"
        private const val URL_LOGIN="auth/login"
        private const val URL_ADDRESS ="address/"
        private const val URL_ADDRESS_POST ="address"
        private const val URL_ORDER_POST="orders"
        private const val URL_ORDER_GET="orders/"


        fun getCategory(): String{
            return "${Config.BASE_URL + URL_CATEGORY}"
        }

        fun getSubCategoryByCatId(catId: Int):String{
            return "${Config.BASE_URL + URL_SUB_CATEGORY + catId}"
        }

        fun getProductBySubId(subId: Int): String{
            return "${Config.BASE_URL + URL_PRODUCT_BY_SUB_ID + subId}"
        }
        fun getRegister():String{
            return "${Config.BASE_URL+ URL_REGISTER}"
        }
        fun getLogin():String
        {
            return "${Config.BASE_URL+ URL_LOGIN}"
        }
        fun getAddress(userId:String):String
        {
            return "${Config.BASE_URL+ URL_ADDRESS + userId}"
        }
        fun postAddress():String{
            return "${Config.BASE_URL+ URL_ADDRESS_POST}"
            //return "http://grocery-second-app.herokuapp.com/api/address"
           // http://grocery-second-app.herokuapp.com/api/address
        }
        fun deleteAddress(userId:String):String
        {
            return "${Config.BASE_URL+ URL_ADDRESS + userId}"
        }
        fun updateAddress(id:String):String{
            return "${Config.BASE_URL+ URL_ADDRESS +id}"
        }
        fun postOrders():String{
            return "${Config.BASE_URL+ URL_ORDER_POST}"
        }
        fun getOrders(userId:String):String{
            return "${Config.BASE_URL+ URL_ORDER_GET+userId}"
        }
    }

}