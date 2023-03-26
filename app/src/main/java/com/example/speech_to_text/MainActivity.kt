package com.example.speech_to_text

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.view.ActionMode
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat.startActivityForResult
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    val RQ_SPEECH_REC  = 102
    lateinit var btn_speech_to_text: Button
    lateinit var text_display: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_speech_to_text = findViewById(R.id.btn_button)
        text_display = findViewById(R.id.tv_text)

        btn_speech_to_text.setOnClickListener {

            speech_recognition()

        }
    }

    fun speech_recognition() {

        if (!SpeechRecognizer.isRecognitionAvailable(this)){
            Toast.makeText(this,"Speech recognition not available", Toast.LENGTH_SHORT).show()
        } else{
            val mintent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            mintent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            mintent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            mintent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hey , Speak Something  ")

            startActivityForResult(mintent,RQ_SPEECH_REC)
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == RQ_SPEECH_REC && resultCode == Activity.RESULT_OK){

            val result = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            tv_text.text = result?.get(0).toString()

        }
    }


}