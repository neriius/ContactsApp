package com.example.contactsapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.contactsapp.Options
import com.example.contactsapp.R
import com.example.contactsapp.databinding.FragmentEditProfileBinding

class EditProfileFragment:Fragment() {

    private lateinit var binding: FragmentEditProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditProfileBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (Options.FEATURE_NAVIGATION_ENABLED){
            binding.saveBtn.setOnClickListener() {
                val navHostFragment = parentFragmentManager.findFragmentById(R.id.fragments_container) as NavHostFragment
                val navController = navHostFragment.navController
                navController.navigate(R.id.action_editProfileFragment_to_myProfileFragment)
                    /*view.findNavController()
                        .navigate(R.id.action_editProfileFragment_to_myProfileFragment)
*/
            }
        }else{
            binding.saveBtn.setOnClickListener() {
                parentFragmentManager.beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.fragments_container, MyProfileFragment())
                    .commit()
            }
        }

    }
}