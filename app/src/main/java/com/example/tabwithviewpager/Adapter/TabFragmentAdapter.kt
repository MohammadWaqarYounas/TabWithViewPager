package com.example.tabwithviewpager.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class TabFragmentAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm!!) {
    private val fragmentList: MutableList<Fragment> = ArrayList<Fragment>()
    private val fragmentTitleList: MutableList<String> = ArrayList()
    override fun getItem(i: Int): Fragment {
        return fragmentList[i]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentTitleList[position]
    }

    fun addFragment(fragment: Fragment, title: String) {
        fragmentList.add(fragment)
        fragmentTitleList.add(title)
    }
}