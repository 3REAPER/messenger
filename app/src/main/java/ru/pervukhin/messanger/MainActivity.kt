package ru.pervukhin.messanger

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment


class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        navController = navHostFragment.navController

    }

    fun navigateToChatList(){
        navController.navigate(R.id.action_signFragment_to_chatListFragment)
    }

    fun navigateToDialogFromChatList(){
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

    fun navigateToDialogFromContactList(){
        navController.navigate(R.id.action_contactListFragment_to_dialogFragment)
    }

    fun navigateToContactFromChatList(){
        navController.navigate(R.id.action_chatListFragment_to_contactListFragment)
    }

    fun navigateToSearchFromChatList(){
        navController.navigate(R.id.action_chatListFragment_to_searchFragment)
    }

    fun navigateToChatListFromSearch(){
        navController.navigate(R.id.action_searchFragment_to_chatListFragment)
    }

    fun navigateToDialogFromSearch(){
        navController.navigate(R.id.action_searchFragment_to_dialogFragment)
    }

    override fun onStart() {
        super.onStart()
        this.stopService(Intent(this,MessageNotificationService::class.java))
    }

    override fun onPause() {
        super.onPause()
        this.startService(Intent(this,MessageNotificationService::class.java))
    }
}
