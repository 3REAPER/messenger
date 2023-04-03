package ru.pervukhin.messanger.fragments.sign

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_sign.view.*
import ru.pervukhin.messanger.App
import ru.pervukhin.messanger.MainActivity
import ru.pervukhin.messanger.R
import ru.pervukhin.messanger.domain.Profile

class SignFragment : Fragment() {

    private lateinit var viewModel: SignViewModel

    private lateinit var mainActivity: MainActivity
    private lateinit var app: App

    companion object {
        private const val NAME_SHARED_PREFERENCES = "profile"
        private const val PROFILE_LOGIN_SHARED_PREFERENCES = "login"
        private const val PROFILE_PASSWORD_SHARED_PREFERENCES = "password"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(SignViewModel::class.java)
        val sharedPreferences = requireContext().getSharedPreferences(NAME_SHARED_PREFERENCES, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val view = inflater.inflate(R.layout.fragment_sign, container, false)
        val sign = view.sign
        val login = view.login
        val password = view.password
        val condition = view.condition
        val registration = view.registration
        mainActivity = activity as MainActivity
        app = mainActivity.application as App

        viewModel.resultLiveData.observe(viewLifecycleOwner){
            it.body().let {
                when (it?.result.toString()){
                    "true" -> {
                        editor.clear()
                        editor.putString(PROFILE_LOGIN_SHARED_PREFERENCES,it?.profile?.login)
                        editor.putString(PROFILE_PASSWORD_SHARED_PREFERENCES,it?.profile?.password)
                        editor.apply()
                        sign(it?.profile!!)
                    }
                    "false" -> condition.text = R.string.wrong_password_login.toString()
                    "Логин не верный" -> R.string.wrong_password_login.toString()
                }
            }
        }

        val loginSharedPreference = sharedPreferences.getString(PROFILE_LOGIN_SHARED_PREFERENCES,"")
        val passwordSharedPreference = sharedPreferences.getString(PROFILE_PASSWORD_SHARED_PREFERENCES,"")
        if (loginSharedPreference != "" && passwordSharedPreference != "") {
            if (loginSharedPreference != null && passwordSharedPreference != null) {
                viewModel.sign(loginSharedPreference, passwordSharedPreference)

            }
        }

        sign.setOnClickListener {
            viewModel.sign(login.text.toString(),password.text.toString())
        }

        registration.setOnClickListener{
            mainActivity.navigateToRegistration()
        }
        return view
    }

    private fun sign(user: Profile){
        app.user = user
        mainActivity.navigateToChatList()
    }


}