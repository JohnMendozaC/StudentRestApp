package com.lupesoft.studentrestapp.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.lupesoft.studentrestapp.api.dto.StudentDto
import com.lupesoft.studentrestapp.data.Resource
import com.lupesoft.studentrestapp.data.StudentRepository

class StudentViewModel @ViewModelInject constructor(
    private val repository: StudentRepository
) : ViewModel() {
    val users: LiveData<Resource<List<StudentDto>>> = repository.getStudents().asLiveData()

    val user: LiveData<Resource<List<StudentDto>>> = repository.getStudents().asLiveData()

    fun saveStudent(id: Int, body: StudentDto) =
        repository.saveStudent(id, body).asLiveData()

    fun updateStudent(id: Int, body: StudentDto) =
        repository.updateStudent(id, body).asLiveData()

    fun deleteStudent(id: Int) =
        repository.deleteStudent(id).asLiveData()

    fun getStudent(id: Int) =
        repository.getStudent(id).asLiveData()
}