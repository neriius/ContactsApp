package com.example.contactsapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.contactsapp.adapters.FragmentsViewPagerAdapter
import com.example.contactsapp.databinding.ActivityFragmentsBinding
import com.example.contactsapp.fragments.ContactsBookFragment
import com.example.contactsapp.fragments.MyProfileFragment

class FragmentsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFragmentsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFragmentsBinding.inflate(layoutInflater)

        setContentView(binding.root)

    }
}