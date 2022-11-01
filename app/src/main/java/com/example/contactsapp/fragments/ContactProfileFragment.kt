package com.example.contactsapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.contactsapp.interfaces.Constants
import com.example.contactsapp.R
import com.example.contactsapp.data.ContactData
import com.example.contactsapp.databinding.FragmentContactProfileBinding
import com.example.contactsapp.objects.ImageLoader

class ContactProfileFragment() : Fragment() {

    private lateinit var binding: FragmentContactProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContactProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val contact = arguments?.get(Constants.CONTACT_BUNDLE_KEY) as ContactData

        ImageLoader.loadImageInView(contact.contactIconUrl,binding.profileImage, this)

        with(binding) {
            nameTextView.text = contact.contactName
            careerTextView.text = contact.contactCareer
            homeAddressTextView.text = contact.homeAddress
        }

        binding.arrowBackBtn.setOnClickListener() {
            navigateToFragment(R.id.action_contactProfileFragment_to_contactsFragment)
        }

    }

    /**
     * Navigate from this fragment to another
     * @param navigationAction action id to navigate
     */
    private fun navigateToFragment(navigationAction: Int) {
        findNavController().navigate(navigationAction)
    }

}