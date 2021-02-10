package com.osamuelalmeida.myapplication.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.text.DateFormat

@Entity(tableName = "task_table")
@Parcelize
data class Task (
    val name: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val dateCreated: Long = System.currentTimeMillis(),
    val isImportant: Boolean = false,
    var isCompleted: Boolean = false
) : Parcelable {
    val createdDateFormatted: String
        get() = DateFormat.getDateInstance().format(dateCreated)
}