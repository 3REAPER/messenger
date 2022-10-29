package ru.pervukhin.messanger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import ru.pervukhin.messanger.repository.Repository

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        navController = navHostFragment.navController
        //navController.navigate(R.id.action_chatListFragment_to_signFragment)
    }

    fun navigateToChatList(){
        navController.navigate(R.id.action_signFragment_to_chatListFragment)
    }

    fun navigateToDialog(){
        navController.navigate(R.id.action_chatListFragment_to_dialogFragment)
    }

    fun navigateToRegistration(){
        navController.navigate(R.id.action_signFragment_to_registrationFragment)
    }

    fun navigateToSign(){
        navController.navigate(R.id.action_registrationFragment_to_signFragment)
    }

    fun navigateToDialogFromChatSettings(){
        navController.navigate(R.id.action_chatSettingsFragment_to_dialogFragment)
    }

    fun navigateToChatListFromDialog(){
        navController.navigate(R.id.action_dialogFragment_to_chatListFragment)
    }

    fun navigateToChatSettingFromDialog(){
        navController.navigate(R.id.action_dialogFragment_to_chatSettingsFragment)
    }
}
