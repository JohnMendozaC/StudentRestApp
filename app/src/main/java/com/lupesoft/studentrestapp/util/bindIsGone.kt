package com.lupesoft.studentrestapp.util

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lupesoft.studentrestapp.api.dto.StudentDto
import com.lupesoft.studentrestapp.fragments.StudentAdapter

@BindingAdapter("isGone")
fun bindIsGone(view: View, isGone: Boolean) {
    view.visibility = if (isGone) {
        View.GONE
    } else {
        View.VISIBLE
    }
}

@BindingAdapter("setDataUser")
fun setDataUser(recycler: RecyclerView, data: List<StudentDto>?) {
    data?.also {
        (recycler.adapter as StudentAdapter).submitList(it)
    }
}
