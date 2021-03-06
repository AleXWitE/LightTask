package com.akashtanov.lighttask.lighttask.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
        @PrimaryKey
        val id: Int,
        val remindId: Int,
        val name: String,
        val date: Long,
        val currentDay: Long,
        val taskRemindDate: Long = 0,
        val listOfSubtasks: MutableList<String> = arrayListOf(),
        val isCompound: Boolean = false,
        val groupName: String = ""
)