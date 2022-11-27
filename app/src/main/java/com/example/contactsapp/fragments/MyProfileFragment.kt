package com.example.contactsapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.contactsapp.R
import com.example.contactsapp.data.ContactData
import com.example.contactsapp.databinding.FragmentMyProfileBinding
import com.example.contactsapp.interfaces.Constants
import com.example.contactsapp.interfaces.OnPagerNavigateBtnClicked
import com.example.contactsapp.objects.ImageLoader
import com.example.contactsapp.objects.Navigator

class MyProfileFragment(private val onPagerNavigateBtnClicked: OnPagerNavigateBtnClicked) : Fragment() {

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

        binding.viewMyContactsBtn.setOnClickListener() {
            onPagerNavigateBtnClicked.swipeToNextFragment()
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