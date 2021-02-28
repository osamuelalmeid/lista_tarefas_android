package com.osamuelalmeida.myapplication.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.osamuelalmeida.myapplication.TestUtil
import com.osamuelalmeida.myapplication.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class TaskDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: TaskDatabase
    private lateinit var dao: TaskDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TaskDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.taskDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun getTasksSortedByName() = runBlockingTest {
        val taskItem1 = TestUtil.createTask1()
        val taskItem2 = TestUtil.createTask2()
        val taskItem3 = TestUtil.createTask3()
        dao.insert(taskItem1)
        dao.insert(taskItem2)
        dao.insert(taskItem3)

        val allTasksItems = dao.getTasksSortedByName("", false).asLiveData().getOrAwaitValue()
        assertThat(allTasksItems.first()).isEqualTo(taskItem2)
    }

    @Test
    fun getTasksSortedByDateCreated() = runBlockingTest {
        val taskItem1 = TestUtil.createTask1()
        val taskItem2 = TestUtil.createTask2()
        val taskItem3 = TestUtil.createTask3()
        dao.insert(taskItem1)
        dao.insert(taskItem2)
        dao.insert(taskItem3)

        val allTasksItems = dao.getTasksSortedByDateCreated("", false).asLiveData().getOrAwaitValue()
        assertThat(allTasksItems.first()).isEqualTo(taskItem3)
    }

    @Test
    fun insertTaskItem() = runBlockingTest {
        val taskItem = TestUtil.createTask1()
        dao.insert(taskItem)

        val allTasksItems = dao.getTasks("", SortOrder.BY_NAME, false).asLiveData().getOrAwaitValue()
        assertThat(allTasksItems).contains(taskItem)
    }

    @Test
    fun updateTaskItem() = runBlockingTest {
        val taskItem = Task("task 1")
        dao.insert(taskItem)
        val taskItemUpdated = TestUtil.createTask1()
        dao.update(taskItemUpdated)

        val allTaskItems = dao.getTasks("", SortOrder.BY_NAME, false).asLiveData().getOrAwaitValue()
        assertThat(allTaskItems).containsNoDuplicates()
        assertThat(allTaskItems).doesNotContain(taskItem)
        assertThat(allTaskItems).contains(taskItemUpdated)
    }

    @Test
    fun deleteTaskItem() = runBlockingTest {
        val taskItem = TestUtil.createTask1()
        dao.insert(taskItem)
        dao.delete(taskItem)

        val allTaskItems = dao.getTasks("", SortOrder.BY_NAME, false).asLiveData().getOrAwaitValue()
        assertThat(allTaskItems).doesNotContain(taskItem)
    }

    @Test
    fun deleteCompletedTasks() = runBlockingTest {
        val taskItem1 = TestUtil.createTask1()
        val taskItem2 = TestUtil.createTask2()
        val taskItem3 = TestUtil.createTask3()
        dao.insert(taskItem1)
        dao.insert(taskItem2)
        dao.insert(taskItem3)
        dao.deleteCompletedTasks()

        val allTaskItems = dao.getTasks("", SortOrder.BY_NAME, false).asLiveData().getOrAwaitValue()
        assertThat(allTaskItems).doesNotContain(taskItem1)
        assertThat(allTaskItems).doesNotContain(taskItem3)
        assertThat(allTaskItems).contains(taskItem2)
    }

}