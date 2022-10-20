package com.example.contactsapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactsapp.interfaces.OnContactIconClicked
import com.example.contactsapp.interfaces.Options
import com.example.contactsapp.R
import com.example.contactsapp.adapters.ContactsAdapter

import com.example.contactsapp.data.ContactData
import com.example.contactsapp.databinding.FragmentContactsBinding
import com.example.contactsapp.viewModels.ContactsViewModel

class ContactsFragment : Fragment(), OnContactIconClicked {

    private lateinit var binding: FragmentContactsBinding
    private lateinit var adapter: ContactsAdapter
    private val contactsViewModel: ContactsViewModel by activityViewModels()

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

        setListeners()
        createContactsRecycleView()
    }

    /**
     * sets navigation buttons listeners
     */
    private fun setListeners() {
        if (Options.FEATURE_NAVIGATION_ENABLED) {
            binding.arrowBackBtn.setOnClickListener() {
                navigateToFragment(R.id.action_contactsFragment_to_myProfileFragment)
            }

            binding.addContactBtn.setOnClickListener() {
                navigateToFragment(R.id.action_contactsFragment_to_addContactFragment)
            }

        } else {
            binding.arrowBackBtn.setOnClickListener {
                transactToFragment(MyProfileFragment())
            }

            binding.addContactBtn.setOnClickListener {
                transactToFragment(AddContactFragment())
            }
        }
    }

    private fun createContactsRecycleView() {
        val contactsList = contactsViewModel.getContacts()

        adapter = ContactsAdapter(contactsList.value as ArrayList<ContactData>?, this, this)
        val layoutManager = LinearLayoutManager(context)
        binding.contactsRecycleView.adapter = adapter
        binding.contactsRecycleView.layoutManager = layoutManager

        contactsViewModel.contactsLiveData.observe(viewLifecycleOwner, Observer {
            adapter.setContacts(it as ArrayList<ContactData>)
        })
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

    override fun sendContactBundle(contactBundle: Bundle, contactView: View) {
        if (Options.FEATURE_NAVIGATION_ENABLED == true) {
            view?.findNavController()
                ?.navigate(R.id.action_contactsFragment_to_contactProfileFragment, contactBundle)
        } else {
            val contactProfileFragment = ContactProfileFragment();
            contactProfileFragment.arguments = contactBundle;
            parentFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fragments_container, contactProfileFragment)
                .commit()
        }
    }

}
