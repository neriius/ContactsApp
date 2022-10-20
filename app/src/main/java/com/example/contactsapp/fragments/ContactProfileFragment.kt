package com.example.contactsapp.fragments

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.contactsapp.interfaces.Constants
import com.example.contactsapp.interfaces.Options
import com.example.contactsapp.R
import com.example.contactsapp.data.ContactData
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
            homeAddressTextView.text = contact.homeAddress
        }

        binding.arrowBackBtn.setOnClickListener() {
            if (Options.FEATURE_NAVIGATION_ENABLED) {
                navigateToFragment(R.id.action_contactProfileFragment_to_contactsFragment)
            } else {
                transactToFragment(ContactsFragment())
            }
        }


    }


    private fun loadImageToContactProfile(contact: ContactData) {
        Glide.with(this)
            .load(contact.contactIconUrl)
            .error(R.drawable.default_contact_icon)
            .circleCrop()
            .into(binding.profileImage)
    }

    /**
     * Navigate from this fragment to another
     * @param navigationAction action id to navigate
     */
    private fun navigateToFragment(navigationAction: Int) {
        findNavController().navigate(navigationAction)
    }

    /**
     * Make transact from this fragment to another
     * @param fragment to transact to
     */
    private fun transactToFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragments_container, fragment)
            .commit()
    }
}