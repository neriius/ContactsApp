package com.example.contactsapp.objects

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

object Navigator {
    fun navigateToFragment(fragment: Fragment, navigateAction: Int) {
        fragment.findNavController().navigate(navigateAction)
    }

    fun navigateAndSendBundleToFragment(
        fragment: Fragment,
        navigateAction: Int,
        dataBundle: Bundle
    ) {
        fragment.findNavController().navigate(navigateAction, dataBundle)
    }
}