package com.example.contactsapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.contactsapp.fragments.ContactsBookFragment
import com.example.contactsapp.fragments.MyProfileFragment

class FragmentsViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    private val fragmentsList = listOf<Fragment>(MyProfileFragment(), ContactsBookFragment())

    override fun getItemCount(): Int {
        return fragmentsList.size
    }

    override fun createFragment(position: Int): Fragment {
        println(position)
        return fragmentsList[position];
    }
}