package com.shiqiliu.onlineshopping1.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shiqiliu.onlineshopping1.R
import com.shiqiliu.onlineshopping1.activity.ImageActivity
import com.shiqiliu.onlineshopping1.activity.ProductDetailAcivity
import com.shiqiliu.onlineshopping1.appurl.Config
import com.shiqiliu.onlineshopping1.modules.Product
import com.shiqiliu.onlineshopping1.modules.Product.Companion.KEY_Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_product_detail_acivity.view.*
import kotlinx.android.synthetic.main.fragment_product.view.*
import kotlin.collections.ArrayList

class AdapterProduct(var mContext: Context)
    :RecyclerView.Adapter<AdapterProduct.MyViewHolder>(){
    var mList :ArrayList<Product> = ArrayList()
    inner class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        fun bind(product:Product){
            //q2
            itemView.text_view_product_name_fragment.text = product.productName
            itemView.text_view_product_price_fragment.text="$${product.price.toString()}"
            itemView.text_view_product_unit_fragment.text = product.unit
            //itemView.text_view_product_mrp_detail.text=product.mrp.toString()
            Picasso.get()
                .load("${Config.IMAGE_URL+product.image}")
                .into(itemView.image_view_fragment)

            itemView.setOnClickListener {
                var intent = Intent(mContext,ProductDetailAcivity::class.java)
                intent.putExtra(KEY_Product,product)
                mContext.startActivity(intent)
            }
            itemView.image_view_fragment.setOnClickListener {
                var intent = Intent(mContext,ImageActivity::class.java)
                intent.putExtra(KEY_Product,product)
                mContext.startActivity(intent)
            }

        }
    }



    fun setData(list: ArrayList<Product>){
        mList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view = LayoutInflater.from(mContext).inflate(R.layout.fragment_product,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var product = mList[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}