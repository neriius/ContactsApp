package com.example.contactsapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.contactsapp.adapters.FragmentsViewPagerAdapter
import com.example.contactsapp.databinding.FragmentViewPagerBinding
import com.example.contactsapp.interfaces.OnPagerNavigateBtnClicked
import com.example.contactsapp.viewModels.ViewPagerViewModel

class ViewPagerFragment : Fragment(), OnPagerNavigateBtnClicked {

    private lateinit var binding : FragmentViewPagerBinding
    private val viewPagerViewModel: ViewPagerViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentViewPagerBinding.inflate(inflater, container, false)


        val fragmentsToSwipe = listOf<Fragment>(MyProfileFragment(this), ContactsBookFragment(this))
        val fragmentsViewPagerAdapter = FragmentsViewPagerAdapter(fragmentsToSwipe,requireActivity().supportFragmentManager, lifecycle)

        val viewPager = binding.pager

        viewPager.adapter = fragmentsViewPagerAdapter
        viewPager.setCurrentItem(viewPagerViewModel.getLastFragmentPosition(),false)

        return binding.root
    }

    override fun onPause() {
        super.onPause()
        viewPagerViewModel.setLastFragmentPosition(binding.pager.currentItem);
    }

    override fun swipeToPrevFragment() {
        binding.pager.currentItem = binding.pager.currentItem - 1;
    }

    override fun swipeToNextFragment() {
        binding.pager.currentItem = binding.pager.currentItem + 1;
    }


}