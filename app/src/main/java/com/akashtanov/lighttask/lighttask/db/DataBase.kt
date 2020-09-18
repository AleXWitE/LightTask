package com.akashtanov.lighttask.lighttask.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.akashtanov.lighttask.lighttask.db.daos.GroupDao
import com.akashtanov.lighttask.lighttask.db.daos.ReminderDao
import com.akashtanov.lighttask.lighttask.db.daos.TaskDao
import com.akashtanov.lighttask.lighttask.db.entities.Group
import com.akashtanov.lighttask.lighttask.db.entities.Reminder
import com.akashtanov.lighttask.lighttask.db.entities.Task

@Database(entities = [Task::class, Group::class, Reminder::class], version = 1)
@TypeConverters(SubtasksConverter::class)
abstract class DataBase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun groupDao(): GroupDao
    abstract fun reminderDao(): ReminderDao
}