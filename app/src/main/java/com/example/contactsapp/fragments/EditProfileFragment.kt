package com.example.contactsapp.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.contactsapp.interfaces.Options
import com.example.contactsapp.R
import com.example.contactsapp.data.ContactData
import com.example.contactsapp.databinding.FragmentEditProfileBinding
import com.example.contactsapp.interfaces.Constants

class EditProfileFragment() : Fragment() {

    private lateinit var binding: FragmentEditProfileBinding
    private var imageURI: String = ""
    private lateinit var openGalleryActivity: ActivityResultLauncher<Intent>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        openGalleryActivity = getOpenGalleryIntent()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.profileImage.setOnClickListener() {
            loadImageFromGallery()
        }



        if (Options.FEATURE_NAVIGATION_ENABLED) {
            binding.saveBtn.setOnClickListener() {
                var contact: ContactData
                with(binding) {
                    contact = ContactData(
                        imageURI,
                        profileUsername.text.toString(),
                        profileCareer.text.toString(),
                        profileAddress.text.toString()
                    )
                }
                navigateToFragment(R.id.action_editProfileFragment_to_myProfileFragment, contact)
            }
        } else {
            binding.saveBtn.setOnClickListener() {
                transactToFragment(MyProfileFragment())
            }
        }

    }

    /**
     * Navigate from this fragment to another
     * @param navigationAction action id to navigate
     */
    private fun navigateToFragment(navigationAction: Int, contact: ContactData) {
        findNavController().navigate(
            navigationAction,
            bundleOf(Constants.CONTACT_BUNDLE_KEY to contact)
        )
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

    private fun loadImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        openGalleryActivity.launch(intent)
    }

    /**
     * Returns ActivityResultLauncher<Intent>  that opens the gallery and returns uri of the image
     * that user choose and loads image in profile image view
     * */
    private fun getOpenGalleryIntent(): ActivityResultLauncher<Intent> {
        return registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // There are no request codes
                val data: Intent? = result.data
                imageURI = data?.data.toString()
                Glide.with(this)
                    .load(imageURI)
                    .error(R.drawable.default_contact_icon)
                    .circleCrop()
                    .into(binding.profileImage)
            }
        }
    }
}