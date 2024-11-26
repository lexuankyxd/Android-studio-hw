package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView.AdapterContextMenuInfo
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
  val students = mutableListOf(
    StudentModel("Nguyễn Văn An", "SV001"),
    StudentModel("Trần Thị Bảo", "SV002"),
    StudentModel("Lê Hoàng Cường", "SV003"),
    StudentModel("Phạm Thị Dung", "SV004"),
  )
  val studentAdapter = StudentAdapter(this, students)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    // TODO: Thiet lap thuoc tinh thanh action bar
      val toolbar: Toolbar = findViewById(R.id.toolbar)
      toolbar.setTitle("Student Management App")
      setSupportActionBar(toolbar)



    val listView = findViewById<ListView>(R.id.listView)
    listView.adapter = studentAdapter

    registerForContextMenu(listView)
  }

  // TODO: Ham khoi tao cho context menu
  override fun onCreateContextMenu(
    menu: ContextMenu?,
    v: View?,
    menuInfo: ContextMenu.ContextMenuInfo?
  ) {
    menuInflater.inflate(R.menu.long_click_item, menu)
    super.onCreateContextMenu(menu, v, menuInfo)
  }

  private val editLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
    if (result.resultCode == RESULT_OK) {
      val data = result.data
      val hoten = data?.getStringExtra("hoten")
      val mssv = data?.getStringExtra("mssv")
      val pos = data?.getIntExtra("pos", -1)
      if (hoten != null && mssv != null && pos != null && pos != -1) {
        students[pos].studentName = hoten
        students[pos].studentId = mssv
        studentAdapter.notifyDataSetChanged()
      }
    }
  }

  private val addLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
    if (result.resultCode == RESULT_OK) {
      val data = result.data
      val hoten = data?.getStringExtra("hoten")
      val mssv = data?.getStringExtra("mssv")
      if (hoten != null && mssv != null) {
        students.add(StudentModel(hoten, mssv))
        studentAdapter.notifyDataSetChanged() // Refresh adapter
      }
    }
  }
  // TODO: Ham xu ly su kien nhan vao context menu
  override fun onContextItemSelected(item: MenuItem): Boolean {
    val pos = (item.menuInfo as AdapterContextMenuInfo).position
    when (item.itemId) {
      R.id.action_delete -> {students.removeAt(pos); studentAdapter.notifyDataSetChanged()}
      R.id.action_edit -> {
        val intent = Intent(this, EditStudentActivity::class.java).apply{
          putExtra("name", students[pos].studentName)
          putExtra("id", students[pos].studentId)
          putExtra("pos", pos)
        }
        editLauncher.launch(intent)
      }
    }
    return super.onContextItemSelected(item)
  }

  // TODO: Ham khoi tao option menu
  override fun onCreateOptionsMenu(menu: Menu): Boolean {
      Log.d("option menu", "onCreateOptionsMenu: ")
      val inflater: MenuInflater = menuInflater
      inflater.inflate(R.menu.main_menu, menu)
      return true
  }
  // TODO: Ham xu ly su kien nhan vao option menu
  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      R.id.action_addNew -> {
        val intent = Intent(this, AddStudentActivity::class.java)
        addLauncher.launch(intent)
      }
    }
    return super.onOptionsItemSelected(item)
  }

}