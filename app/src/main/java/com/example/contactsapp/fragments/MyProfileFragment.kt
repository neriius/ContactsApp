package com.example.contactsapp.fragments

import android.media.Image
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.contactsapp.R
import com.example.contactsapp.adapters.FragmentsViewPagerAdapter
import com.example.contactsapp.data.ContactData
import com.example.contactsapp.databinding.FragmentMyProfileBinding
import com.example.contactsapp.interfaces.Constants
import com.example.contactsapp.objects.ImageLoader
import com.example.contactsapp.objects.Navigator

class MyProfileFragment : Fragment() {

    private lateinit var binding: FragmentMyProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(arguments != null){
            fillProfileWithDataFromEditProfileFragment()
        }

        binding.editProfileBtn.setOnClickListener() {
            Navigator.navigateToFragment(this,R.id.action_myProfileFragment_to_editProfileFragment)
        }

        binding.viewMyContactsBtn.setOnClickListener() {
            Navigator.navigateToFragment(this, R.id.action_myProfileFragment_to_contactsFragment)
        }
    }

    private fun fillProfileWithDataFromEditProfileFragment() {
        val contact = arguments?.get(Constants.CONTACT_BUNDLE_KEY) as ContactData
        binding.careerTextView.text = contact.contactCareer
        binding.nameTextView.text = contact.contactName
        binding.homeAddressTextView.text = contact.homeAddress
        ImageLoader.loadImageInView(contact.contactIconUrl, binding.profileImage, this)
    }

}