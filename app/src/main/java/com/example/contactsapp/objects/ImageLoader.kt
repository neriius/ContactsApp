package com.example.contactsapp.objects

import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.contactsapp.R

object ImageLoader {
    /**
     * loads image into ImageView
     */
    fun loadImageInView(url:String?, imageView: ImageView, fragment:Fragment){
        Glide.with(fragment)
            .load(url)
            .error(R.drawable.default_contact_icon)
            .circleCrop()
            .into(imageView)
    }

    fun loadImageInView(url:String?, imageView: ImageView, view: View){
        Glide.with(view)
            .load(url)
            .error(R.drawable.default_contact_icon)
            .circleCrop()
            .into(imageView)
    }
}