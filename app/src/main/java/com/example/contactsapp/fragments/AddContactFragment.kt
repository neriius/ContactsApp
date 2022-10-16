package com.example.contactsapp.fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.contactsapp.R
import com.example.contactsapp.data.ContactData
import com.example.contactsapp.databinding.FragmentAddContactBinding
import com.example.contactsapp.viewModels.ContactsViewModel
import java.net.URI


class AddContactFragment : Fragment() {

    private lateinit var binding: FragmentAddContactBinding
    private val contactsViewModel: ContactsViewModel by activityViewModels()
    private var imageURI: String
    private lateinit var openGalleryActivity : ActivityResultLauncher<Intent>

    init {
        imageURI = ""
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddContactBinding.inflate(inflater, container, false)
        openGalleryActivity = getOpenGalleryIntent()
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.profileImage.setOnClickListener() {
            loadImageFromGallery()
        }

        binding.saveBtn.setOnClickListener() {
            contactsViewModel.addContact(createContact())
            view.findNavController().navigate(R.id.action_addContactFragment_to_contactsFragment)
        }
    }

    private fun createContact() = ContactData(
        imageURI,
        binding.usernameTextInput.text.toString(),
        binding.careerTextInput.text.toString()
    )

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