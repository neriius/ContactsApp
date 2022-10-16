package com.example.contactsapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.contactsapp.Constants
import com.example.contactsapp.R
import com.example.contactsapp.data.ContactData
import com.example.contactsapp.databinding.ActivityFragmentsBinding
import com.example.contactsapp.databinding.FragmentContactProfileBinding

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
        loadImageToContactProfile(contact)

        with(binding) {
            nameTextView.text = contact.contactName
            careerTextView.text = contact.contactCareer
        }

        binding.arrowBackBtn.setOnClickListener(){
            view.findNavController().navigate(R.id.action_contactProfileFragment_to_contactsFragment)
        }
    }

    private fun loadImageToContactProfile(contact: ContactData) {
        Glide.with(this)
            .load(contact.contactIconUrl)
            .error(R.drawable.default_contact_icon)
            .circleCrop()
            .into(binding.profileImage)
    }
}