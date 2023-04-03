package ru.pervukhin.messanger.fragments.search

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.get
import kotlinx.android.synthetic.main.fragment_chat_list.view.*
import kotlinx.android.synthetic.main.fragment_chat_list.view.chat_list
import kotlinx.android.synthetic.main.fragment_search.view.*
import ru.pervukhin.messanger.App
import ru.pervukhin.messanger.MainActivity
import ru.pervukhin.messanger.R
import ru.pervukhin.messanger.adapter.ChatListAdapter
import ru.pervukhin.messanger.domain.Chat

class SearchFragment : Fragment(), ChatListAdapter.ChatOnClickListener {
    private lateinit var viewModel: SearchViewModel
    private lateinit var mainActivity: MainActivity
    private lateinit var app: App

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        mainActivity = activity as MainActivity
        app = mainActivity.application as App
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        val searchEditText = view.name
        val recyclerView = view.chat_list
        val adapter = ChatListAdapter(this)

        recyclerView.adapter = adapter
        searchEditText.addTextChangedListener {
            viewModel.search(searchEditText.text.toString())
        }

        viewModel.liveData.observe(viewLifecycleOwner){ list ->
            list?.let {
                adapter.setList(it)
            }
        }

        return view
    }

    override fun onClick(chat: Chat) {
        app.chat = chat
        mainActivity.navigateToDialogFromSearch()
    }
}