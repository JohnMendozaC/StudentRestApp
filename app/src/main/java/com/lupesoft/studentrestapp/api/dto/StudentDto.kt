package com.lupesoft.studentrestapp.api.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StudentDto(
    @field:SerializedName("idEstudiante") val idStudent: Int? = null,
    @field:SerializedName("nombreEstudiante") val nameStudent: String? = null,
    @field:SerializedName("codigoEstudiante") val codeStudent: Long? = null
):Parcelable
