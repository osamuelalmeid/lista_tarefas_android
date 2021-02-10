package com.osamuelalmeida.myapplication.ui.deleteAllCompletedTasks

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.osamuelalmeida.myapplication.data.TaskDao
import com.osamuelalmeida.myapplication.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class DeleteAllCompletedTasksViewModel @ViewModelInject constructor(
        private val taskDao: TaskDao,
        @ApplicationScope private val applicationScope: CoroutineScope
): ViewModel() {

    fun onConfirmClick() = applicationScope.launch {
        taskDao.deleteCompletedTasks()
    }

}