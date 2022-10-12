package com.example.a4hw1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a4hw1.databinding.TaskItemBinding
import com.example.a4hw1.room.TaskModel

class TaskAdapter(private val list: List<TaskModel>, private val listener: TaskClickListener) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            TaskItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class TaskViewHolder(val binding: TaskItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(taskModel: TaskModel) {
            binding.titleTv.text = taskModel.task
            binding.regularTv.text = taskModel.regular
            binding.dateTv.text = taskModel.date
            itemView.setOnClickListener {
                listener.itemClick(taskModel)
            }
            itemView.setOnLongClickListener {
                listener.deleteItemClick(taskModel)
                false
            }
        }

    }

}