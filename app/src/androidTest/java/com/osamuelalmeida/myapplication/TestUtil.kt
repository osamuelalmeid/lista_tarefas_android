package com.osamuelalmeida.myapplication

import com.osamuelalmeida.myapplication.data.Task

object TestUtil {

    fun createTask1() = Task(
        name = "Cultivar horta",
        id = 1,
        dateCreated = 1614218836367,
        isImportant = false,
        isCompleted = true
    )

    fun createTask2() = Task(
        name = "Buscar carro no mecânico",
        id = 2,
        dateCreated = 1614218802421,
        isImportant = false,
        isCompleted = false
    )

    fun createTask3() = Task(
        name = "Fazer yoga",
        id = 3,
        dateCreated = 1614218741252,
        isImportant = false,
        isCompleted = true
    )

}