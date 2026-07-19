package com.example.schooladmissionsystem.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.schooladmissionsystem.data.AppDatabase
import com.example.schooladmissionsystem.data.ApplicationRepository
import com.example.schooladmissionsystem.data.StudentApplication
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ApplicationViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ApplicationRepository
    
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    val filteredApplications: StateFlow<List<StudentApplication>>

    init {
        val applicationDao = AppDatabase.getDatabase(application).applicationDao()
        repository = ApplicationRepository(applicationDao)
        
        filteredApplications = repository.allApplications
            .combine(_searchQuery) { applications, query ->
                if (query.isBlank()) {
                    applications
                } else {
                    applications.filter {
                        it.studentName.contains(query, ignoreCase = true)
                    }
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )
    }

    fun onSearchQueryChange(newQuery: String) {
        _searchQuery.value = newQuery
    }

    fun addApplication(name: String, age: Int, grade: String) {
        viewModelScope.launch {
            val newApplication = StudentApplication(
                studentName = name,
                age = age,
                grade = grade
            )
            repository.insert(newApplication)
        }
    }

    fun deleteApplication(application: StudentApplication) {
        viewModelScope.launch {
            repository.delete(application)
        }
    }
}
