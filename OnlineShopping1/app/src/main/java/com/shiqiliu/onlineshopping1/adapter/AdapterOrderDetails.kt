package com.shiqiliu.onlineshopping1.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.shiqiliu.onlineshopping1.fragment.FragmentOrderDetailsImage
import com.shiqiliu.onlineshopping1.fragment.FragmentOrderDetailsSummary
import com.shiqiliu.onlineshopping1.modules.Order


class AdapterOrderDetails (fm:FragmentManager):FragmentPagerAdapter(fm){
    var mFragment : ArrayList<Fragment> = ArrayList()
    var mTitle: ArrayList<String> = ArrayList()
    override fun getCount(): Int {
       // return mFragment.size
      return  2
    }

    override fun getItem(position: Int): Fragment {
       // return mFragment[position]
        return when(position){
            0->FragmentOrderDetailsSummary()
            else->FragmentOrderDetailsImage()
        }
    }
    fun addFragment(order: Order){
        mFragment.add(FragmentOrderDetailsSummary.newInstance(order))
    }

    override fun getPageTitle(position: Int): CharSequence? {
      //  return mTitle[position]
        return when(position){
            0->"Order Summary"
            else->"Item"
        }

    }

}