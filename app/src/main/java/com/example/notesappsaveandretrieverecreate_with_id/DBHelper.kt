package com.example.notesappsaveandretrieverecreate_with_id

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import androidx.core.content.contentValuesOf

class DBHelper(context: Context):SQLiteOpenHelper(context,"Notes",null,1) {
    val dbWrite:SQLiteDatabase=writableDatabase
    val reade:SQLiteDatabase=readableDatabase
    override fun onCreate(p0: SQLiteDatabase?) {

        if (p0 != null) {
            p0.execSQL("create table Notes(id integer PRIMARY KEY,note text)")
        }
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) { }

    fun addNote(text:String): Long {
        val contentValues=ContentValues()
        contentValues.put("note",text)
       return dbWrite.insert("Notes",null,contentValues)
    }

    fun getNotes(): ArrayList<String> {
        val notes=ArrayList<String>()
        val cursor:Cursor=reade.query("Notes",null,null,null,null,null,null)
        cursor.moveToFirst()
        while (cursor.moveToNext()){
            var note=cursor.getString(cursor.getColumnIndex("note"))
            notes.add(note)
            cursor.moveToNext()
            Log.d("asdf645685",note)
        }
        cursor.close()
        return notes
    }
}