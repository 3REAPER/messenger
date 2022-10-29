package ru.pervukhin.messanger.fragments.sign

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_sign.view.*
import ru.pervukhin.messanger.App
import ru.pervukhin.messanger.MainActivity
import ru.pervukhin.messanger.R

class SignFragment : Fragment() {

    private lateinit var viewModel: SignViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(this).get(SignViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_sign, container, false)
        val sign = view.sign
        val login = view.login
        val password = view.password
        val condition = view.condition
        val registration = view.registration
        val activity = activity as MainActivity
        val application = activity.application as App

        sign.setOnClickListener(View.OnClickListener {
            viewModel.sign(login.text.toString(),password.text.toString())
            viewModel.resultLiveData.observe(viewLifecycleOwner){
                it.body().let {
                    when (val result = it?.result.toString()){
                    "true" -> {
                        activity.navigateToChatList()
                        application.user = it?.profile!!
                    }
                    "false" -> condition.text = "Пароль или логин не верный"
                    "Логин не верный" -> condition.text = result
                    }

                }

            }

        })

        registration.setOnClickListener(View.OnClickListener {
            activity.navigateToRegistration()
        })
        return view
    }


}