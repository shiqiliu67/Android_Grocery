package com.shiqiliu.onlineshopping1.adapter


import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.shiqiliu.onlineshopping1.R
import com.shiqiliu.onlineshopping1.activity.ShoppingCartActivity
import com.shiqiliu.onlineshopping1.appurl.Config
import com.shiqiliu.onlineshopping1.database.DBHelper
import com.shiqiliu.onlineshopping1.modules.Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_shopping_cart.view.*
import kotlinx.android.synthetic.main.row_shopping_cart_adapter.view.*

class AdapterShoppingCart(var mContext: Context) :
    RecyclerView.Adapter<AdapterShoppingCart.MyViewHolder>(){
    var dbHelper = DBHelper(mContext)
    var mList = dbHelper.getAllProduct()
   // lateinit var totalPrice :Float
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(product: Product){
            itemView.text_view_product_name_cart.text = product.productName
            itemView.text_view_product_price_cart.text = "$${product.price.toString()}"
            itemView.text_view_show_unit_cart.text = product.quantity.toString()
            Picasso.get().load("${Config.IMAGE_URL+product.image}").into(itemView.image_view_cart)

            itemView.image_view_minus_cart.setOnClickListener {
                dbHelper.decreaseNumber(product)
                mContext.startActivity(Intent(mContext,ShoppingCartActivity::class.java))
                itemView.text_view_show_unit_cart.text = product.quantity.toString()
                Log.d("abc", "count --")
                }

            itemView.image_view_add_cart.setOnClickListener {
                dbHelper.increaseNumber(product)
                itemView.text_view_show_unit_cart.text = product.quantity.toString()
                mContext.startActivity(Intent(mContext,ShoppingCartActivity::class.java))
                Log.d("abc", "count ++")
             //   Toast.makeText(mContext,"Count Changed Successful", Toast.LENGTH_SHORT).show()
            }

            itemView.image_view_delete_cart.setOnClickListener {
                dbHelper.deleteProductToCart(product)
                mContext.startActivity(Intent(mContext,ShoppingCartActivity::class.java))
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view = LayoutInflater.from(mContext).inflate(R.layout.row_shopping_cart_adapter,parent,false)
        var myViewHolder =MyViewHolder(view)
        return myViewHolder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
    var product = mList[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int {
        return mList.size
    }
    fun setData(list: ArrayList<Product>){
        mList = list
        notifyDataSetChanged()
    }

}