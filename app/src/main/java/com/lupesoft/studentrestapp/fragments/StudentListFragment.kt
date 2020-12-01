package com.lupesoft.studentrestapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.lupesoft.studentrestapp.api.dto.StudentDto
import com.lupesoft.studentrestapp.data.Status
import com.lupesoft.studentrestapp.databinding.FragmentStudentListBinding
import com.lupesoft.studentrestapp.viewmodels.StudentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StudentListFragment : Fragment() {

    private val viewModel: StudentViewModel by viewModels()
    private lateinit var binding: FragmentStudentListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStudentListBinding.inflate(inflater, container, false)
        context ?: return binding.root
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loader.isLoading = false
        binding.userList.adapter = StudentAdapter()
        viewModel.users.observe(viewLifecycleOwner) { result ->
            binding.apply {
                when (result.status) {
                    Status.LOADING -> loader.isLoading = false
                    Status.SUCCESS -> {
                        listUser = result.data
                        loader.isLoading = true
                    }
                    Status.ERROR -> {
                        loader.isLoading = true
                        listUser = listOf(
                            StudentDto(0, "Jhon Mendoza", 20202678001),
                            StudentDto(0, "Camilo Gamba", 20202678004)
                        )
                        Toast.makeText(context, "Error con la red", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}