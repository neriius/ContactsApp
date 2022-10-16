package com.example.contactsapp.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.contactsapp.data.ContactData

class ContactsViewModel : ViewModel() {

    private val _contactsMutableLiveData = MutableLiveData<List<ContactData>>()
    val contactsLiveData: LiveData<List<ContactData>> = _contactsMutableLiveData

    init {
        _contactsMutableLiveData.value = ArrayList()
    }

     fun addContact(contact: ContactData){
        _contactsMutableLiveData.value = _contactsMutableLiveData.value?.plus(contact);
    }

     fun getContacts() : LiveData<List<ContactData>> {
        return contactsLiveData
    }
}