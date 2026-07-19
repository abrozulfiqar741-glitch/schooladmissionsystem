package com.example.schooladmissionsystem.data

import kotlinx.coroutines.flow.Flow

class ApplicationRepository(private val applicationDao: ApplicationDao) {
    val allApplications: Flow<List<StudentApplication>> = applicationDao.getAllApplications()

    suspend fun insert(application: StudentApplication) {
        applicationDao.insertApplication(application)
    }

    suspend fun delete(application: StudentApplication) {
        applicationDao.deleteApplication(application)
    }
}
