package com.example.schooladmissionsystem.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ApplicationDao {
    @Query("SELECT * FROM student_applications ORDER BY id DESC")
    fun getAllApplications(): Flow<List<StudentApplication>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertApplication(application: StudentApplication)

    @Delete
    suspend fun deleteApplication(application: StudentApplication)
}
