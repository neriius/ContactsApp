package com.example.contactsapp.data

import android.os.Parcel
import android.os.Parcelable

data class ContactData(val contactIconUrl: String?, val contactName: String?, val contactCareer: String?, val homeAddress:String?) :Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun describeContents(): Int {
       return 0
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {

    }

    companion object CREATOR : Parcelable.Creator<ContactData> {
        override fun createFromParcel(parcel: Parcel): ContactData {
            return ContactData(parcel)
        }

        override fun newArray(size: Int): Array<ContactData?> {
            return arrayOfNulls(size)
        }
    }
}
