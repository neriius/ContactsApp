package com.example.contactsapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.contactsapp.R
import com.example.contactsapp.adapters.FragmentsViewPagerAdapter
import com.example.contactsapp.databinding.FragmentViewPagerBinding

class ViewPagerFragment : Fragment() {

    private lateinit var binding : FragmentViewPagerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewPagerBinding.inflate(inflater, container, false)

        val fragmentsViewPagerAdapter = FragmentsViewPagerAdapter(requireActivity().supportFragmentManager, lifecycle);
        val viewPager = binding.pager
        viewPager.adapter = fragmentsViewPagerAdapter

        return binding.root
    }
}