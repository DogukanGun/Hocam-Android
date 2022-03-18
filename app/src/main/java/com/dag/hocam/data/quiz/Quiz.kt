package com.dag.hocam.data.quiz

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Quiz(
    val id: Int,
    val quizName: String,
    val questions: List<QuestionResponse>
):Parcelable
