package com.example.contactsapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactsapp.R
import com.example.contactsapp.adapters.ContactsAdapter
import com.example.contactsapp.data.ContactData
import com.example.contactsapp.databinding.FragmentContactsBinding
import com.example.contactsapp.viewModels.ContactsViewModel

class ContactsFragment:Fragment() {

    private lateinit var binding:FragmentContactsBinding
    private lateinit var adapter: ContactsAdapter
    private val contactsViewModel:ContactsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContactsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.arrowBackBtn.setOnClickListener(){
            view.findNavController().navigate(R.id.action_contactsFragment_to_myProfileFragment)
        }

        binding.addContactBtn.setOnClickListener(){
            view.findNavController().navigate(R.id.action_contactsFragment_to_addContactFragment)
        }

        createContactsRecycleView()
    }

    private fun createContactsRecycleView(){
        val contactsList = contactsViewModel.getContacts()

        adapter = ContactsAdapter(contactsList.value as ArrayList<ContactData>?)
        val layoutManager = LinearLayoutManager(context)
        binding.contactsRecycleView.adapter = adapter
        binding.contactsRecycleView.layoutManager = layoutManager

        contactsViewModel.contactsLiveData.observe(viewLifecycleOwner, Observer {
            adapter.setContacts(it as ArrayList<ContactData>)
        })
    }
}