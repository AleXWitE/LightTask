package com.akashtanov.lighttask.lighttask.db.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.akashtanov.lighttask.lighttask.db.entities.Group

@Dao
interface GroupDao : BaseDao<Group> {

    @Query("SELECT * FROM groups")
    fun getAllGroups(): LiveData<List<Group>>

    @Query("UPDATE tasks SET groupName=:newName WHERE groupName=:oldName")
    fun updateTaskGroupName(oldName: String, newName: String)

    @Query("UPDATE tasks SET groupName=\"\" WHERE groupName=:groupName")
    fun deleteTaskGroupName(groupName: String)
}