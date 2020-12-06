package com.example.myapplication

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class viewpagerclass (fragmentManager: FragmentManager):FragmentPagerAdapter(fragmentManager) {
    override fun getCount() =2

    override fun getItem(position: Int): Fragment {
        println("------------------")

        println("---------${position}----------")

        return when (position) {
            0 -> BlankFragment.newInstance()
            1 -> homeFragment.newInstance()
            else -> homeFragment.newInstance()
        }
    }
}