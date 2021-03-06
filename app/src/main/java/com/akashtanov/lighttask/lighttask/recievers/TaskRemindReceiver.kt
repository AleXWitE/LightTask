package com.akashtanov.lighttask.lighttask.recievers

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.akashtanov.lighttask.lighttask.utils.Constants
import com.skushnaryov.lighttask.lighttask.R
import com.akashtanov.lighttask.lighttask.activities.MainActivity

class TaskRemindReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val text = intent.getStringExtra(Constants.EXTRAS_REMIND_TEXT)
        val id = intent.getIntExtra(Constants.EXTRAS_ID, -1)

        if (text == null || id == -1) {
            return
        }

        val clickIntent = Intent(context, MainActivity::class.java)
        val clickPending = PendingIntent.getActivity(context, 0, clickIntent, 0)
        val builder = NotificationCompat.Builder(context, Constants.TASKS_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_action_notification)
                .setContentTitle(context.getString(R.string.taskRemindRecieverTitle))
                .setContentIntent(clickPending)
                .setContentText(text)
                .setAutoCancel(true)

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            builder.priority = NotificationCompat.PRIORITY_HIGH
            builder.setDefaults(NotificationCompat.DEFAULT_ALL)
        }

        NotificationManagerCompat.from(context).notify(id, builder.build())
    }
}