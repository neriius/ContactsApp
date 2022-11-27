package com.example.contactsapp.viewModels

import androidx.lifecycle.ViewModel

class ViewPagerViewModel : ViewModel() {

    private var lastFragmentPosition : Int = 0;

    fun setLastFragmentPosition(position:Int){
        lastFragmentPosition = position;
    }

    fun getLastFragmentPosition() : Int{
        return lastFragmentPosition;
    }
}