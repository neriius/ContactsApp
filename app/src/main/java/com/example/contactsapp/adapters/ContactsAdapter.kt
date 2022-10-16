package com.example.contactsapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.contactsapp.R
import com.example.contactsapp.data.ContactData
import com.example.contactsapp.databinding.ContactItemViewBinding
import com.example.contactsapp.viewModels.ContactsViewModel

class ContactsAdapter(private var contacts: ArrayList<ContactData>?) :
    RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ContactItemViewBinding.inflate(inflater, parent, false)
        return ContactsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        val contact = contacts!![position]
        holder.binding.trashImageBtn.setOnClickListener(){
            contacts!!.removeAt(holder.adapterPosition )
            notifyItemRemoved(holder.adapterPosition )
            println("Adapter" + holder.adapterPosition)
        }
        holder.bind(contact)
    }

    override fun getItemCount(): Int {
        return contacts?.size ?: 0
    }

    fun setContacts(list: ArrayList<ContactData>) {
        contacts = list
    }

    class ContactsViewHolder(val binding: ContactItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(contact: ContactData) {
            with(binding) {
                contactName.text = contact.contactName
                contactCareerTextView.text = contact.contactCareer

                Glide.with(itemView)
                    .load(contact.contactIconUrl)
                    .error(R.drawable.default_contact_icon)
                    .circleCrop()
                    .into(binding.contactIcon)
            }
        }

    }

}