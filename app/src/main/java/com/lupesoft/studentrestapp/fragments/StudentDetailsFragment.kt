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
import com.lupesoft.studentrestapp.databinding.FragmentStudentDetailsBinding
import com.lupesoft.studentrestapp.viewmodels.StudentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StudentDetailsFragment : Fragment() {

    private val viewModel: StudentViewModel by viewModels()
    private lateinit var binding: FragmentStudentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStudentDetailsBinding.inflate(inflater, container, false)
        context ?: return binding.root
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.user = arguments?.get("student") as StudentDto
        binding.loader.isLoading = true
        onClicks()
        /*viewModel.getStudent(0).observe(viewLifecycleOwner) { result ->
            binding.apply {
                when (result.status) {
                    Status.LOADING -> loader.isLoading = false
                    Status.SUCCESS -> {
                        user = result.data
                        loader.isLoading = true
                    }
                    Status.ERROR -> {
                        user = null
                    }
                }
            }
        }*/
    }

    private fun onClicks() {
        binding.saveUser.setOnClickListener {
            binding.loader.isLoading = false
            val student = StudentDto(
                0,
                binding.studentItemName.text.toString(),
                binding.studentItemCode.text.toString().toLong()
            )

            viewModel.saveStudent(0, student).observe(viewLifecycleOwner) { result ->
                binding.apply {
                    when (result.status) {
                        Status.LOADING -> loader.isLoading = false
                        Status.SUCCESS -> {
                            Toast.makeText(context, "Guardo con exito", Toast.LENGTH_LONG).show()
                            loader.isLoading = true
                        }
                        Status.ERROR -> {
                            Toast.makeText(context, "Error con la red", Toast.LENGTH_LONG).show()
                            loader.isLoading = true
                        }
                    }
                }
            }
        }

        binding.updateUser.setOnClickListener {
            binding.loader.isLoading = false
            val student = StudentDto(
                0,
                binding.studentItemName.text.toString(),
                binding.studentItemCode.text.toString().toLong()
            )
            viewModel.updateStudent(binding.user?.idStudent ?: 0, student)
                .observe(viewLifecycleOwner) { result ->
                    binding.apply {
                        when (result.status) {
                            Status.LOADING -> loader.isLoading = false
                            Status.SUCCESS -> {
                                Toast.makeText(context, "Actualizo con exito", Toast.LENGTH_LONG)
                                    .show()
                                loader.isLoading = true
                            }
                            Status.ERROR -> {
                                Toast.makeText(context, "Error con la red", Toast.LENGTH_LONG)
                                    .show()
                                loader.isLoading = true
                            }
                        }
                    }
                }
        }

        binding.deleteUser.setOnClickListener {
            binding.loader.isLoading = false
            viewModel.deleteStudent(binding.user?.idStudent ?: 0).observe(viewLifecycleOwner) { result ->
                binding.apply {
                    when (result.status) {
                        Status.LOADING -> loader.isLoading = false
                        Status.SUCCESS -> {
                            Toast.makeText(context, "Guardo con exito", Toast.LENGTH_LONG).show()
                            loader.isLoading = true
                        }
                        Status.ERROR -> {
                            Toast.makeText(context, "Error con la red", Toast.LENGTH_LONG).show()
                            loader.isLoading = true
                        }
                    }
                }
            }
        }
    }
}