package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class StudentAdapter(private val context: Context, val students: List<StudentModel>): BaseAdapter() {
  private lateinit var studentId : TextView
  private lateinit var studentName : TextView
  override fun getCount(): Int {
    return students.size
  }
  override fun getItem(position: Int): Any {
    return position
  }
  override fun getItemId(position: Int): Long {
    return position.toLong()
  }
  override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
    var convertView = convertView
    convertView = LayoutInflater.from(context).inflate(R.layout.student_item, parent, false)
    studentId = convertView.findViewById(R.id.studentId)
    studentName = convertView.findViewById(R.id.studentName)
    studentId.text = students[position].studentId
    studentName.text = students[position].studentName
    return convertView
  }
}