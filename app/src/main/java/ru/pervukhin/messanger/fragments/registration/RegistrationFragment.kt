package ru.pervukhin.messanger.fragments.registration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_registration.view.*
import ru.pervukhin.messanger.App
import ru.pervukhin.messanger.MainActivity
import ru.pervukhin.messanger.R
import ru.pervukhin.messanger.domain.Profile

class RegistrationFragment : Fragment() {


    private lateinit var viewModel: RegistrationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_registration, container, false)
        viewModel = ViewModelProvider(this).get(RegistrationViewModel::class.java)
        val name = view.name
        val login = view.login
        val number = view.number
        val password = view.password
        val repeatPassword = view.repeat_password
        val registration = view.registration
        val condition = view.condition
        val sign = view.sign
        val mainActivity = activity as MainActivity

        registration.setOnClickListener{
            if (password.text.toString() == repeatPassword.text.toString()){
                val profile = Profile(name.text.toString(), login.text.toString(), number.text.toString(), password.text.toString())
                viewModel.registration(profile)
                viewModel.liveData.observe(viewLifecycleOwner){
                    it.body().let {
                        when (it){
                        "Success" ->{
                            Toast.makeText(context, "Вы упешно зарегистрировались", Toast.LENGTH_LONG).show()
                            mainActivity.navigateToSign()
                        }
                        "LoginUsed" -> condition.text = "Этот логин уже использовался"
                        }
                    }
                }
            }else{
                condition.text = "Пароли не совпадют"
            }
        }

        sign.setOnClickListener{
            mainActivity.navigateToSign()
        }

        return view
    }


}