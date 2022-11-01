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
import com.example.contactsapp.R
import com.example.contactsapp.data.ContactData
import com.example.contactsapp.databinding.FragmentEditProfileBinding
import com.example.contactsapp.interfaces.Constants
import com.example.contactsapp.objects.ImageLoader
import com.example.contactsapp.objects.Navigator

class EditProfileFragment() : Fragment() {

    private lateinit var binding: FragmentEditProfileBinding
    private var imageURI: String = ""
    private lateinit var openGalleryActivity: ActivityResultLauncher<Intent>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        openGalleryActivity = getOpenGalleryIntent()
        binding.profileImage.setOnClickListener() {
            loadImageFromGallery()
        }

        binding.saveBtn.setOnClickListener() {
            val contact: ContactData = createContactFromFields()
            val contactBundle = bundleOf(Constants.CONTACT_BUNDLE_KEY to contact)
            Navigator.navigateAndSendBundleToFragment(
                this,
                R.id.action_editProfileFragment_to_myProfileFragment,
                contactBundle
            )
        }

    }

    private fun createContactFromFields(): ContactData{
        var contact: ContactData
        with(binding) {
            contact = ContactData(
                imageURI,
                profileUsername.text.toString(),
                profileCareer.text.toString(),
                profileAddress.text.toString()
            )
        }
        return contact;
    }
    /**
     * Get image from gallery
     */
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
                ImageLoader.loadImageInView(imageURI, binding.profileImage, this)
            }
        }
    }
}