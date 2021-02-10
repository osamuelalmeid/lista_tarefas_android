package com.osamuelalmeida.myapplication.ui.tasks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.osamuelalmeida.myapplication.data.Task
import com.osamuelalmeida.myapplication.databinding.ItemTaskBinding

class TasksAdapter(private val listener: OnItemClickListener): ListAdapter<Task, TasksAdapter.TasksViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TasksViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class TasksViewHolder(private val binding: ItemTaskBinding): RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
                root.setOnClickListener {
                    if (adapterPosition != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(adapterPosition))
                    }
                }
                itemCheckBox.setOnClickListener {
                    if (adapterPosition != RecyclerView.NO_POSITION) {
                        listener.onCheckBoxClick(getItem(adapterPosition), itemCheckBox.isChecked)
                    }
                }
            }
        }

        fun bind(task: Task) {
            binding.apply {
                itemCheckBox.isChecked = task.isCompleted
                itemTitle.text = task.name
                itemTitle.paint.isStrikeThruText = task.isCompleted
                labelPriority.isVisible = task.isImportant
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(task: Task)
        fun onCheckBoxClick(task: Task, isChecked: Boolean)
    }

    class DiffCallback: DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Task, newItem: Task) =
            oldItem == newItem

    }

}