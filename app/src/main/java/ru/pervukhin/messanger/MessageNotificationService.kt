package ru.pervukhin.messanger

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Handler
import android.os.IBinder
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import ru.pervukhin.messanger.repository.Repository
import javax.inject.Inject

class GetMessageService : Service() {
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable
    private lateinit var app: App
    private val repository: Repository = Repository()

    override fun onBind(intent: Intent): IBinder {
        throw UnsupportedOperationException("Not yet implemented")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        println("working")
        handler = Handler()
        runnable = Runnable { getUnread() }
        handler.postDelayed(runnable,5000)
        app = application as App
        return START_STICKY
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getUnread(){
        println("working")
        runBlocking(Dispatchers.IO){
            val messages = repository.messageService.getUnread(app.user.id).body()
            if (messages != null) {
                if (messages.isNotEmpty()){


                    val notification = NotificationCompat.Builder(baseContext,createNotificationChannel(baseContext,"1","UnReadMessage"))
                        .setSmallIcon(R.drawable.ic_send)
                        .setContentTitle("Новое сообщение")
                        .setContentText(messages.get(0).message)
                        .build()

                    val notificationManager = NotificationManagerCompat.from(baseContext)
                    notificationManager.notify(1, notification)
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun createNotificationChannel(
        context: Context,
        channelId: String,
        channelName: String
    ): String {
        val chan = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
        chan.lightColor = Color.BLUE
        chan.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
        val service = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        service.createNotificationChannel(chan)
        return channelId
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(runnable)
    }
}