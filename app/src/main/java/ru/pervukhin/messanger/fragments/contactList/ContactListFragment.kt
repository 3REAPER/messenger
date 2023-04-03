package ru.pervukhin.messanger.fragments.contactList

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_contact_list.view.*
import ru.pervukhin.messanger.App
import ru.pervukhin.messanger.MainActivity
import ru.pervukhin.messanger.R
import ru.pervukhin.messanger.adapter.ContactAdapter
import kotlin.collections.List
import kotlin.collections.MutableMap
import kotlin.collections.distinct
import kotlin.collections.forEach
import kotlin.collections.listOf
import kotlin.collections.mutableMapOf
import kotlin.collections.plus
import kotlin.collections.set

class ContactListFragment : Fragment(), ContactAdapter.OnClickListenerOpenChat {
    private lateinit var viewModel: ContactListViewModel
    private lateinit var mainActivity: MainActivity
    private lateinit var app: App

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(ContactListViewModel::class.java)
        mainActivity = activity as MainActivity
        app = mainActivity.application as App
        val view = inflater.inflate(R.layout.fragment_contact_list, container, false)
        val recyclerViewContact = view.recycler_view_contact
        val  adapter = ContactAdapter(this)
        recyclerViewContact.adapter = adapter

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

        viewModel.liveDataProfile.observe(viewLifecycleOwner){
            if (it != null){
                adapter.setList(it)
            }
        }


        return view
    }


    @SuppressLint("Range")
    private fun readContact() {
        var result: List<MutableMap<String,String>> = listOf()
        val contentResolver = requireContext().contentResolver
        val cursor =
            contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null)
        if (cursor?.moveToFirst()!!) {
            do {
                result = result.plus(mutableMapOf("number" to cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))))

            } while (cursor.moveToNext())

        }
        result.forEach{
            val key = "number"
            if (it[key]?.get(0)?.toString().equals("8")){
                it[key] = "+7" + it[key]?.removeRange(0,1)
            }
            if (it[key]?.get(0)?.toString() != "+"){
                it[key] = "+" +it[key]
            }
        }
        cursor.close()
        viewModel.getProfileByNumber(result.distinct())

    }

    override fun onClickOpenChat(idProfile: Int) {
        viewModel.getChat(app.user.id, idProfile)
        viewModel.liveDataChat.observe(viewLifecycleOwner){
            app.chat = it
            mainActivity.navigateToDialogFromContactList()
        }
    }
}