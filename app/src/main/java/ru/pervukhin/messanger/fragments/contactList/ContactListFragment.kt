package ru.pervukhin.messanger.fragments.contactList

import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.pm.PackageManager
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.provider.ContactsContract
import android.util.ArrayMap
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.get
import kotlinx.android.synthetic.main.fragment_contact_list.view.*
import ru.pervukhin.messanger.MainActivity
import ru.pervukhin.messanger.R
import ru.pervukhin.messanger.adapter.ContactAdapter

class ContactListFragment : Fragment() {
    private lateinit var viewModel: ContactListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(ContactListViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_contact_list, container, false)
        val recyclerViewContact = view.recycler_view_contact
        recyclerViewContact.adapter = ContactAdapter()

        val permissionStatus =
            ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_CONTACTS)

        if (permissionStatus == PackageManager.PERMISSION_GRANTED) {
            readContact()
        }else {
            ActivityCompat.requestPermissions(
                activity as MainActivity, arrayOf(Manifest.permission.READ_CONTACTS),
                1
            )
        }


        return view
    }

    private fun readContact() {
        val result: MutableMap<String,String> = mutableMapOf()
        val contentResolver = requireContext().contentResolver
        val cursor =
            contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null)
        if (cursor?.moveToFirst()!!) {
            do {
                result += cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)) to cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
            } while (cursor.moveToNext());

        }
        result.forEach{
            val key = it.key
            if (result[key]?.get(0)?.toString().equals("8")){
                result[key] = "+7" + result[key]?.removeRange(0,1)
            }
            if (result[key]?.get(0)?.toString() != "+"){
                result[key] = "+" +result[key]
            }
        }
        cursor.close()

    }

}