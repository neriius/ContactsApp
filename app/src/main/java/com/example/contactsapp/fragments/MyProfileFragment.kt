package com.example.contactsapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.contactsapp.interfaces.Options
import com.example.contactsapp.R
import com.example.contactsapp.data.ContactData
import com.example.contactsapp.databinding.FragmentMyProfileBinding
import com.example.contactsapp.interfaces.Constants

class MyProfileFragment : Fragment() {

    private lateinit var binding: FragmentMyProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments?.containsKey(Constants.CONTACT_BUNDLE_KEY) == true) {
            fillProfileWithDataFromEditProfileFragment()
        }

        binding.editProfileBtn.setOnClickListener() {
            if (Options.FEATURE_NAVIGATION_ENABLED == true) {
                navigateToFragment(R.id.action_myProfileFragment_to_editProfileFragment)
            } else {
                transactToFragment(EditProfileFragment())
            }
        }

        binding.viewMyContactsBtn.setOnClickListener() {
            if (Options.FEATURE_NAVIGATION_ENABLED == true) {
                navigateToFragment(R.id.action_myProfileFragment_to_contactsFragment)
            } else {
                transactToFragment(ContactsFragment())
            }
        }
    }

    private fun fillProfileWithDataFromEditProfileFragment() {
        val contact = arguments?.get(Constants.CONTACT_BUNDLE_KEY) as ContactData
        binding.careerTextView.text = contact.contactCareer
        binding.nameTextView.text = contact.contactName
        binding.homeAddressTextView.text = contact.homeAddress
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