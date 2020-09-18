package com.akashtanov.lighttask.lighttask.recievers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationManagerCompat
import com.akashtanov.lighttask.lighttask.utils.Constants
import com.skushnaryov.lighttask.lighttask.R
import com.akashtanov.lighttask.lighttask.db.repositories.TaskRepository
import com.akashtanov.lighttask.lighttask.toast

class TaskDoneReciever : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val id = intent.getIntExtra(Constants.EXTRAS_ID, -1)

        if (id != -1) {
            TaskRepository().deleteById(id)
            context.toast(context.getString(R.string.taskCompleted))
        }

        NotificationManagerCompat.from(context).cancel(id)
    }
}