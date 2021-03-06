package com.akashtanov.lighttask.lighttask.recievers

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.os.bundleOf
import com.akashtanov.lighttask.lighttask.utils.Constants
import com.skushnaryov.lighttask.lighttask.R
import com.akashtanov.lighttask.lighttask.activities.MainActivity
import com.akashtanov.lighttask.lighttask.utils.NotificationUtils

class ReminderReciever : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val id = intent.getIntExtra(Constants.EXTRAS_ID, -1)
        val remindName = intent.getStringExtra(Constants.EXTRAS_NAME)
        val reminderTime = intent.getLongExtra(Constants.EXTRAS_TIME_REPEAT, -1L)
        if (id != -1 && !remindName.isEmpty() || reminderTime != -1L) {
            createNotification(id, remindName, reminderTime, context)
        }
    }

    private fun createNotification(id: Int, reminderName: String, reminderTime: Long, context: Context) {
        val reminderIntent = Intent(context, MainActivity::class.java)
        val reminderPending = PendingIntent.getActivity(context, 0, reminderIntent, 0)

        val offIntent = Intent(context, ReminderOffReciever::class.java).apply {
            action = Constants.REMINDER_OFF_RECIEVER
            putExtras(bundleOf(
                    Constants.EXTRAS_ID to id,
                    Constants.EXTRAS_NAME to reminderName,
                    Constants.EXTRAS_TIME_REPEAT to reminderTime
            ))
        }
        val offPending = PendingIntent.getBroadcast(context, id, offIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(context, Constants.REMINDERS_CHANNELS_ID)
                .setSmallIcon(R.drawable.ic_action_notification)
                .setContentTitle(context.getString(R.string.reminderRecieveTitle))
                .setContentText(reminderName)
                .setContentIntent(reminderPending)
                .setAutoCancel(true)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder.addAction(R.drawable.ic_alarm_off_black_24dp,
                    context.getString(R.string.off), offPending)
        }

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            builder.priority = NotificationCompat.PRIORITY_HIGH
            builder.setDefaults(NotificationCompat.DEFAULT_ALL)
        }

        NotificationManagerCompat.from(context).notify(id, builder.build())

        NotificationUtils.crtOrRmvReminderNotification(context, id, reminderName, reminderTime)
    }
}