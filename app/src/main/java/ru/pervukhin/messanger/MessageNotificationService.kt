package ru.pervukhin.messanger

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import ru.pervukhin.messanger.domain.ConditionSend
import ru.pervukhin.messanger.domain.Message
import ru.pervukhin.messanger.domain.Profile
import ru.pervukhin.messanger.repository.Repository
import java.util.*
import javax.inject.Inject

class MessageNotificationService : Service() {
    private lateinit var timer: Timer
    private lateinit var app: App
    private val repository: Repository = Repository()

    @Inject
    lateinit var user: Profile

    override fun onBind(intent: Intent): IBinder {
        throw UnsupportedOperationException("Not yet implemented")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        App.appComponent.inject(this)
        timer = Timer()
        timer.schedule(object : TimerTask(){
            override fun run() {
                sendNotification()
            }
        },0,1L * 1000)
        return START_STICKY
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun sendNotification(){
        runBlocking(Dispatchers.IO){
            val messages = repository.getUnread(user.id).body()
            if (messages != null) {
                if (messages.isNotEmpty()){
                    val notificationManager = NotificationManagerCompat.from(baseContext)
                    if (messages.size == 1){
                        switchCondition(messages.get(0))
                        notificationManager.notify(1, createMessage(messages.get(0).message, false))
                    }else if (messages.size > 1){
                        val text = StringBuffer()
                        for (message in messages){
                            switchCondition(message)
                            text.append(message.message).append("\n")
                        }
                        notificationManager.notify(1, createMessage(text.toString(), true))
                    }
                }
            }
        }
    }

    private suspend fun switchCondition(message: Message) {
        message.conditionSend[0].condition = ConditionSend.CONDITION_SEND
        repository.updateMessage(message)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createMessage(text: String, isManyMassage: Boolean): Notification {
        val title = if (isManyMassage){
            "Новые сообщения"
        }else{
            "Новое сообщение"
        }
        return NotificationCompat.Builder(baseContext,createNotificationChannel(baseContext,"1","UnReadMessage"))
            .setSmallIcon(R.drawable.ic_send)
            .setContentTitle(title)
            .setContentText(text)
            .build()

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun createNotificationChannel(
        context: Context,
        channelId: String,
        channelName: String
    ): String {
        val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
        channel.lightColor = Color.BLUE
        channel.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
        val service = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        service.createNotificationChannel(channel)
        return channelId
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
    }
}