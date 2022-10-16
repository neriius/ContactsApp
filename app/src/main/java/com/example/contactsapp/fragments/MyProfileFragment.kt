package com.example.contactsapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.contactsapp.R
import com.example.contactsapp.databinding.FragmentMyProfileBinding

class MyProfileFragment : Fragment(){

    private lateinit var binding:FragmentMyProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyProfileBinding.inflate(inflater, container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.editProfileBtn.setOnClickListener(){
                view:View->
            view.findNavController().navigate(R.id.action_myProfileFragment_to_editProfileFragment)
        }
        binding.viewMyContactsBtn.setOnClickListener(){
            view:View-> view.findNavController().navigate(R.id.action_myProfileFragment_to_contactsFragment)
        }
    }
}