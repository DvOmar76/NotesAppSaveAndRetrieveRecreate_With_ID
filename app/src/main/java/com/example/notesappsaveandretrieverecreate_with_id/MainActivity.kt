package com.example.notesappsaveandretrieverecreate_with_id

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dbHelper=DBHelper(this)

        var notes=dbHelper.getNotes()
        recyclerView.adapter=RVAdapter(notes)
        recyclerView.layoutManager=LinearLayoutManager(this)
        btnAddNote.setOnClickListener{
            val note=edNote.text.toString()

                if (note.isNotEmpty())
                {
                    val status=dbHelper.addNote(note)
                    if (status!=-1L)
                    {
                        Toast.makeText(applicationContext, "note added", Toast.LENGTH_SHORT).show()
                        dbHelper.addNote(note)
                        notes=dbHelper.getNotes()
//                        recyclerView.adapter?.notifyDataSetChanged()
                        recyclerView.adapter=RVAdapter(notes)
                        recyclerView.layoutManager=LinearLayoutManager(this)
                    }else
                    {
                        Toast.makeText(applicationContext, "error db", Toast.LENGTH_SHORT).show()
                    }
                }else
                {
                    Toast.makeText(applicationContext, "please enter text", Toast.LENGTH_SHORT).show()
                }
        }
    }
}