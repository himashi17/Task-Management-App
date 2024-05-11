package com.example.taskbear

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.taskbear.databinding.ActivityAddNoteBinding
import com.example.taskbear.databinding.ActivityMainBinding

class AddNoteActivity : AppCompatActivity() {

    private lateinit var  binding:ActivityAddNoteBinding
    private lateinit var db:NotesDatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityAddNoteBinding.inflate(layoutInflater)

        enableEdgeToEdge()

        setContentView(binding.root)
        db = NotesDatabaseHelper(this)

        binding.saveButton.setOnClickListener {
            val title = binding.titleEditText.text.toString()
            val content = binding.contentEditText.text.toString()
            val note =Note(0,title,content)
            db.insertNote(note)
            finish()
            Toast.makeText(this,"Task Saved",Toast.LENGTH_SHORT).show()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

}