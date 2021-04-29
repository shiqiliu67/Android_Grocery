package com.shiqiliu.onlineshopping1.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.shiqiliu.onlineshopping1.appurl.Endpoints
import com.shiqiliu.onlineshopping1.fragment.ProductFragment
import com.shiqiliu.onlineshopping1.modules.Category
import com.shiqiliu.onlineshopping1.modules.CategoryResponse
import com.shiqiliu.onlineshopping1.modules.SubCategory

class AdapterSubFragment(fm:FragmentManager) :FragmentPagerAdapter(fm){
    var mFragment : ArrayList<Fragment> = ArrayList()
    var mTitle: ArrayList<String> = ArrayList()
    override fun getCount(): Int {
        return mFragment.size

    }

    override fun getItem(position: Int): Fragment {
        return mFragment[position]
    }
//q1
    fun addFragment(subCategory: SubCategory){
        mTitle.add(subCategory.subName)
        mFragment.add(ProductFragment.newInstance(subCategory.subId))
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mTitle[position]
    }

}