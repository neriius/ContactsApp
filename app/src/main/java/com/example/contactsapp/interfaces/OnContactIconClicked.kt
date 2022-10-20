package com.example.contactsapp.interfaces

import android.os.Bundle
import android.view.View

interface OnContactIconClicked {
    fun sendContactBundle(contactBundle : Bundle, contactView : View)
}