package com.example.contactsapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactsapp.R
import com.example.contactsapp.adapters.ContactsAdapter
import com.example.contactsapp.data.ContactData
import com.example.contactsapp.databinding.FragmentContactsBinding
import com.example.contactsapp.interfaces.OnContactIconClicked
import com.example.contactsapp.interfaces.OnPagerNavigateBtnClicked
import com.example.contactsapp.objects.Navigator
import com.example.contactsapp.viewModels.ContactsViewModel

class ContactsBookFragment(private val onPagerNavigateBtnClicked: OnPagerNavigateBtnClicked) : Fragment(), OnContactIconClicked {

    private lateinit var binding: FragmentContactsBinding
    private lateinit var adapter: ContactsAdapter
    private val contactsViewModel: ContactsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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

        binding.arrowBackBtn.setOnClickListener {
            onPagerNavigateBtnClicked.swipeToPrevFragment();
        }

        binding.addContactBtn.setOnClickListener {
            Navigator.navigateToFragment(
                this,
                R.id.action_viewPagerFragment_to_addContactFragment
            )
        }

        /*binding.arrowBackBtn.setOnClickListener(){
            Navigator.navigateToFragment(this, R.id.action_contactsBookFragment_to_myProfileFragment)
        }*/
    }

    private fun createContactsRecycleView() {
        val contactsList = contactsViewModel.getContacts()

        adapter = ContactsAdapter(contactsList.value as ArrayList<ContactData>?, this, this)
        val layoutManager = LinearLayoutManager(context)
        binding.contactsRecycleView.adapter = adapter
        binding.contactsRecycleView.layoutManager = layoutManager

        contactsViewModel.contactsLiveData.observe(viewLifecycleOwner) {
            adapter.setContacts(it as ArrayList<ContactData>)
        }
    }

    override fun sendContactBundle(contactBundle: Bundle, contactView: View) {
        view?.findNavController()
            ?.navigate(R.id.action_viewPagerFragment_to_contactProfileFragment, contactBundle)

    }

}
