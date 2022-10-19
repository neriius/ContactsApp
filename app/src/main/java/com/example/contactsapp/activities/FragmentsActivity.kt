package com.example.contactsapp.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.contactsapp.Options
import com.example.contactsapp.R
import com.example.contactsapp.databinding.ActivityFragmentsBinding
import com.example.contactsapp.fragments.AddContactFragment
import com.example.contactsapp.fragments.MyProfileFragment

class FragmentsActivity : AppCompatActivity() {

    private lateinit var binding:ActivityFragmentsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFragmentsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Options.FEATURE_NAVIGATION_ENABLED){
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.fragments_container) as NavHostFragment
            val navController = navHostFragment.navController
        }else{
            val myProfileFragment = MyProfileFragment();
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragments_container, myProfileFragment)
                .commit()
        }



    }
}