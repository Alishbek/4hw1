package com.example.a4hw1

import com.example.a4hw1.room.TaskModel

interface TaskClickListener {
    fun itemClick(taskModel: TaskModel)
    fun deleteItemClick(taskModel: TaskModel)
}