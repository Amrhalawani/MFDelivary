package com.aks.shagra.data


import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import com.aks.shagra.R
import com.aks.shagra.ui.main.MainActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


/**
 * Created by amrhalawani on 12,November,2021
 */


class CloudMsgService : FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        if (remoteMessage != null) {
            sendNotification(remoteMessage)
        }
    }

    private fun sendNotification(remoteMessage: RemoteMessage) {
        val intent: Intent
        val messageBody = remoteMessage.notification!!.body
        val title = remoteMessage.notification!!.title

        //if (remoteMessage.getData() != null) {
        intent = Intent(this, MainActivity::class.java)

        remoteMessage.data.forEach {
            Log.e("TAG", "sendNotification: {key, value} = {${it.key}, ${it.value}} ")
            intent.putExtra(it.key, it.value)
        }

        // intent.putExtra(Constants.LESSON_ID, Integer.valueOf(lessonid));
        // Create the TaskStackBuilder and add the intent, which inflates the back stack
        val stackBuilder: TaskStackBuilder = TaskStackBuilder.create(this)
        stackBuilder.addNextIntentWithParentStack(intent)
        // Get the PendingIntent containing the entire back stack
        ////stackBuilder.editIntentAt(0).putExtra(Constants.SECTION_ID, 21);

        //stackBuilder.addParentStack(LessonsActivity.class).editIntentAt(0).putExtra(Constants.SECTION_ID, 21);

        val resultPendingIntent: PendingIntent =
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
                stackBuilder.getPendingIntent(
                    (Math.random() * 100).toInt(),
                    PendingIntent.FLAG_IMMUTABLE
                )
            } else {
                stackBuilder.getPendingIntent(
                    (Math.random() * 100).toInt(),
                    PendingIntent.FLAG_UPDATE_CURRENT
                )
            }
        val CHANNAL_ID = "fcm"
        val notificationBuilder: Notification.Builder =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Notification.Builder(this, CHANNAL_ID)
                    .setSmallIcon(R.drawable.image_logo) //todo change icon
                    .setContentTitle(title)
                    .setContentText(messageBody)
                    .setStyle(Notification.BigTextStyle().bigText(messageBody))
                    .setAutoCancel(true)
                    .setContentIntent(resultPendingIntent)
            } else {
                TODO("VERSION.SDK_INT < O")
            }
        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNAL_ID,
                "Channel human readable title",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build())
    }

    override fun onNewToken(s: String) {
        super.onNewToken(s)
    }
}