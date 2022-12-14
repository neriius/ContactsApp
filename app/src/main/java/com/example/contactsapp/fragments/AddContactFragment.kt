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
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.contactsapp.interfaces.Options
import com.example.contactsapp.R
import com.example.contactsapp.data.ContactData
import com.example.contactsapp.databinding.FragmentAddContactBinding
import com.example.contactsapp.viewModels.ContactsViewModel


class AddContactFragment : Fragment() {

    private lateinit var binding: FragmentAddContactBinding
    private val contactsViewModel: ContactsViewModel by activityViewModels()
    private var imageURI: String
    private lateinit var openGalleryActivity: ActivityResultLauncher<Intent>

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

        if (Options.FEATURE_NAVIGATION_ENABLED) {
            binding.saveBtn.setOnClickListener() {
                contactsViewModel.addContact(createContact())
                navigateToFragment(R.id.action_addContactFragment_to_contactsFragment)
            }
        } else {
            binding.saveBtn.setOnClickListener {
                contactsViewModel.addContact(createContact())
                transactToFragment(ContactsFragment())
            }
        }

    }

    private fun createContact() = ContactData(
        imageURI,
        binding.usernameTextInput.text.toString(),
        binding.careerTextInput.text.toString(),
        binding.addressTextInput.text.toString()
    )

    private fun loadImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        openGalleryActivity.launch(intent)
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