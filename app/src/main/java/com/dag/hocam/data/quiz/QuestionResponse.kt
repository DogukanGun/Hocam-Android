package com.dag.hocam.data.quiz

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class QuestionResponse(
    val id: String,
    var question: String = "",
    var correctAnswer: String,
    var questionUrl:String? = ""
):Parcelable