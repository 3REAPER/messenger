package ru.pervukhin.messanger

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
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

    fun navigateToContactListFromSign(){
        navController.navigate(R.id.action_signFragment_to_contactListFragment)
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
