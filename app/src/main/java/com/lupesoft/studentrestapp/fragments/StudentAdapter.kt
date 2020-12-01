package com.lupesoft.studentrestapp.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lupesoft.studentrestapp.R
import com.lupesoft.studentrestapp.api.dto.StudentDto
import com.lupesoft.studentrestapp.databinding.StudentListItemBinding

class StudentAdapter : ListAdapter<StudentDto, RecyclerView.ViewHolder>(UserDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return UserViewHolder(
            StudentListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val user = getItem(position)
        (holder as UserViewHolder).bind(user, position)
    }


    class UserViewHolder(
        private val binding: StudentListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {


        init {
            binding.setClickListener {
                binding.user?.let { user ->
                    navigateUser(user, it)
                }
            }
        }

        private fun navigateUser(elem: StudentDto, view: View) {
            view.findNavController().navigate(
                R.id.action_studentListFragment_to_studentDetailsFragment, bundleOf(
                    "student" to elem
                )
            )
        }

        fun bind(item: StudentDto, position: Int) {
            binding.apply {
                user = item
                cvUser.tag = position
                executePendingBindings()
            }
        }
    }

}

private class UserDiffCallback : DiffUtil.ItemCallback<StudentDto>() {

    override fun areItemsTheSame(oldItem: StudentDto, newItem: StudentDto): Boolean {
        return oldItem.codeStudent == newItem.codeStudent
    }

    override fun areContentsTheSame(oldItem: StudentDto, newItem: StudentDto): Boolean {
        return oldItem == newItem
    }
}