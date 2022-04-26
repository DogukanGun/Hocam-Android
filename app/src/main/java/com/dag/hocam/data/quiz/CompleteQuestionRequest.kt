package com.dag.hocam.data.quiz

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CompleteQuestionRequest(
    var username:String,
    var quizId:Int,
    var questionIdList:List<Int>
): Parcelable
