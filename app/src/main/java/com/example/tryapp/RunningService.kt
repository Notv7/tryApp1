import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.tryapp.R

class MyForegroundService : Service() {

    companion object {
        private const val NOTIFICATION_ID = 123
        private const val CHANNEL_ID = "ForegroundServiceChannel"
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Create a notification channel (necessary for Android Oreo and above)
        createNotificationChannel()

        // Create a notification
        val notification: Notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Foreground Service")
            .setContentText("Running")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .build()

        // Start the service in the foreground
        startForeground(NOTIFICATION_ID, notification)

        // Perform your long-running operation here

        // If your service is started with START_STICKY, return START_STICKY
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "Foreground Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(serviceChannel)
        }
    }
}
