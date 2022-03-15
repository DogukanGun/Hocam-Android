package com.dag.hocam.ui.admin

import android.R.attr.bitmap
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.dag.hocam.R
import com.dag.hocam.data.quiz.QuestionResponse
import com.dag.hocam.databinding.ItemAddQuizBinding
import com.squareup.picasso.Picasso
import java.io.ByteArrayOutputStream


class AdminAddQuizAdapter(var questionList:List<QuestionResponse>, val context: Context, val quizAmount:Int): RecyclerView.Adapter<AdminAddQuizAdapter.AdminAddQuizViewHolder>() {


    interface AdminAddQuizAdapterListener{
        fun imageClicked(quizId:Int)
    }

    var listener:AdminAddQuizAdapterListener? = null
    inner class AdminAddQuizViewHolder(val binding:ItemAddQuizBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdminAddQuizViewHolder {
        val binding = ItemAddQuizBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AdminAddQuizViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: AdminAddQuizViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.binding.apply {
            val answerList =  listOf("A","B","C","D","E")
            val arrayAdapter = ArrayAdapter(context,android.R.layout.simple_spinner_item,answerList)
            correctAnswerSP.adapter = arrayAdapter
            correctAnswerSP.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    questionList[position].correctAnswer = answerList[p2]
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    questionList[position].correctAnswer = "A"
                }
            }
            imageChooseBTN.setOnClickListener{
                listener?.imageClicked(position)
            }
            if (questionList[position].questionUrl != ""){
                Glide.with(context)
                    .asBitmap()
                    .load(questionList[position].questionUrl)
                    .listener(object :RequestListener<Bitmap>{
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Bitmap>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            TODO("Not yet implemented")
                        }

                        override fun onResourceReady(
                            resource: Bitmap?,
                            model: Any?,
                            target: Target<Bitmap>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {

                            resource?.let {
                                holder.binding.imageChooseBTN.setImageBitmap(it)
                                questionList[position].question = convertToBase64(it)
                            }
                            return true
                        }

                    }).submit()
                this.textView2.visibility = View.GONE
            }else{
                this.imageChooseBTN.setImageDrawable(R.drawable.ic_baseline_help)
            }
            titleTV.text = "Question ${position+1}"
        }
    }

    override fun getItemCount(): Int = quizAmount


    fun convertToBase64(bitmap: Bitmap):String{
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        val byteArray: ByteArray = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

}

private fun ImageView.setImageDrawable(icBaselineHelp: Int) {
    this.setImageDrawable(ContextCompat.getDrawable(context,icBaselineHelp))
}
