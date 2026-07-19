package com.example.schooladmissionsystem.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student_applications")
data class StudentApplication(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val studentName: String,
    val age: Int,
    val grade: String,
    val status: String = "Pending"
)
