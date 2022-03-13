package com.dag.hocam.ui.quiz

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dag.hocam.data.quiz.Quiz
import com.dag.hocam.databinding.ItemTopicBinding

class QuizAdapter(val quizList: List<Quiz>): RecyclerView.Adapter<QuizAdapter.QuizViewHolder>() {

    var listener:QuizListener? = null

    inner class QuizViewHolder(val binding:ItemTopicBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val binding = ItemTopicBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return QuizViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        holder.binding.apply {
            val quiz = quizList[position]
            topicNameTV.text = quiz.quizName
            root.setOnClickListener {
                listener?.quizClicked(quiz)
            }
        }
    }

    override fun getItemCount(): Int = quizList.size
}