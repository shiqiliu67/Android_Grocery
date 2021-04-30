package com.shiqiliu.onlineshopping1.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.shiqiliu.onlineshopping1.fragment.FragmentOrderDetailsImage
import com.shiqiliu.onlineshopping1.fragment.FragmentOrderDetailsSummary
import com.shiqiliu.onlineshopping1.modules.Order


class AdapterOrderDetails (fm:FragmentManager):FragmentPagerAdapter(fm){
    lateinit var fragment1 : Fragment
    lateinit var fragment2 : Fragment
    override fun getCount(): Int {
       // return mFragment.size
      return  2
    }

    override fun getItem(position: Int): Fragment {
       // return mFragment[position]
        return when(position){
            //0->FragmentOrderDetailsSummary()
                0->fragment1
            else->fragment2
        }
    }
    fun addFragment(order: Order){
      //  mFragment.add(FragmentOrderDetailsSummary.newInstance(order))
        fragment1 = FragmentOrderDetailsSummary.newInstance(order)
        //fragment2 = FragmentOrderDetailsImage.newInstance(order.products)
        fragment2 = FragmentOrderDetailsImage.newInstance(order)
    }

    override fun getPageTitle(position: Int): CharSequence? {
      //  return mTitle[position]
        return when(position){
            0->"Order Summary"
            else->"Item"
        }

    }

}