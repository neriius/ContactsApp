package com.example.contactsapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsapp.interfaces.Constants
import com.example.contactsapp.interfaces.OnContactIconClicked
import com.example.contactsapp.data.ContactData
import com.example.contactsapp.databinding.ContactItemViewBinding
import com.example.contactsapp.fragments.ContactsBookFragment
import com.example.contactsapp.objects.ImageLoader

class ContactsAdapter(
    private var contacts: ArrayList<ContactData>?,
    private val contactsBookFragment: ContactsBookFragment,
    private val onContactIconClickedListener: OnContactIconClicked
) :
    RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ContactItemViewBinding.inflate(inflater, parent, false)
        return ContactsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        val contact = contacts!![position]
        holder.binding.trashImageBtn.setOnClickListener() {
            try {
                contacts!!.removeAt(holder.adapterPosition)
                notifyItemRemoved(holder.adapterPosition)
            } catch (e: ArrayIndexOutOfBoundsException) {

            }

        }
        holder.bind(contact, onContactIconClickedListener)
    }

    override fun getItemCount(): Int {
        return contacts?.size ?: 0
    }

    fun setContacts(list: ArrayList<ContactData>) {
        contacts = list
    }

    class ContactsViewHolder(val binding: ContactItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
        /**
         * Initialize contact view with contact data
         */
        fun bind(contact: ContactData, onContactIconClickedListener: OnContactIconClicked) {
            with(binding) {
                contactName.text = contact.contactName
                contactCareerTextView.text = contact.contactCareer

                contactIcon.setOnClickListener() {
                    val bundle = bundleOf(Constants.CONTACT_BUNDLE_KEY to contact)
                    onContactIconClickedListener.sendContactBundle(bundle, itemView);
                }

                ImageLoader.loadImageInView(contact.contactIconUrl, binding.contactIcon, itemView)

            }
        }

    }


}