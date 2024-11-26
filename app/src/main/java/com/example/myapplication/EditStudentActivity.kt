package com.example.myapplication

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

class EditStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        val editHoten = findViewById<EditText>(R.id.edit_hoten)
        val editMssv = findViewById<EditText>(R.id.edit_mssv)
        Log.d("EDIT STUDENT", intent.getStringExtra("name").toString())
        editHoten.setText(intent.getStringExtra("name").toString())
        editMssv.setText(intent.getStringExtra("id").toString())
        // TODO: Su dung setResult de thiet lap ket qua tra ve

        setResult(Activity.RESULT_CANCELED)

        findViewById<Button>(R.id.button_ok).setOnClickListener {
            intent.putExtra("hoten", editHoten.text.toString())
            intent.putExtra("mssv", editMssv.text.toString())
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

        findViewById<Button>(R.id.button_cancel).setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }
}