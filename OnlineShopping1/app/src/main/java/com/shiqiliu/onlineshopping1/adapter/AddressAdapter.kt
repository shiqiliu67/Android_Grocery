package com.shiqiliu.onlineshopping1.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.shiqiliu.onlineshopping1.R
import com.shiqiliu.onlineshopping1.activity.AddressActivity
import com.shiqiliu.onlineshopping1.activity.AddressEditActivity
import com.shiqiliu.onlineshopping1.appurl.Endpoints
import com.shiqiliu.onlineshopping1.modules.Address
import kotlinx.android.synthetic.main.row_address_adapter.view.*
import org.json.JSONObject


class AddressAdapter(var mContext: Context) :RecyclerView.Adapter<AddressAdapter.MyViewHolder>(){
    var mList :ArrayList<Address> = ArrayList()
    var listener: OnAdapterListener? = null
    lateinit var pick_address :Address
    inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(address: Address,position: Int){
            //5 variables
            itemView.text_view_address_city.text = "City: ${address.city}"
            itemView.text_view_address_house_number.text = "HouseNo:${address.houseNo}"
            itemView.text_view_address_pincode.text = "Pincode:${address.pincode.toString()}"
            itemView.text_view_address_street_name.text = "StreetName: ${address.streetName}"
            itemView.text_view_address_type.text ="Type: ${address.type}"
            //delete
            itemView.text_view_delete_address.setOnClickListener {
                var requestQueue = Volley.newRequestQueue(mContext)
                var jsonRequest = StringRequest(
                    Request.Method.DELETE,
                    Endpoints.deleteAddress(address._id),
                    Response.Listener {
                        Toast.makeText(mContext, "Delete Successful", Toast.LENGTH_SHORT).show()
                        Log.d("abc", it.toString())
                        mContext.startActivity(Intent(mContext, AddressActivity::class.java))
                    },
                    Response.ErrorListener {
                        Toast.makeText(mContext, "Address Already Exist", Toast.LENGTH_SHORT).show()
                    }
                )
                requestQueue.add(jsonRequest)
            }
            //edit
            itemView.text_view_edit_address.setOnClickListener {
               var intent = Intent(mContext,AddressEditActivity::class.java)
                //intent.putExtra("id",address._id)
                intent.putExtra("KEY_Address",address)
                mContext.startActivity(intent)
            }
            pick_address =address
            //button
            itemView.button_choose_address.setOnClickListener {
                listener?.onButtonClicked(it, position)
            }
        }
    }
    fun getAddress():Address{
        return pick_address
    }
    interface OnAdapterListener {
        fun onButtonClicked(view: View, position: Int)
    }

    fun setOnAdapterListener(onAdapterListener: OnAdapterListener) {
        listener = onAdapterListener
    }
    fun setData(list: ArrayList<Address>){
        mList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       var view = LayoutInflater.from(mContext).inflate(R.layout.row_address_adapter,parent,false)
       return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var address = mList[position]
        holder.bind(address,position)
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}