package com.example.taskbear

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.taskbear.databinding.ActivityAddNoteBinding
import com.example.taskbear.databinding.ActivityUpdateNoteBinding

class UpdateNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateNoteBinding
    private lateinit var db: NotesDatabaseHelper
    private var noteID:Int = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUpdateNoteBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(binding.root)

        db = NotesDatabaseHelper(this)

        noteID = intent.getIntExtra("note_id", -1)
        if(noteID == -1){
            finish()
            return
        }

        val note = db.getNoteByID(noteID)
        binding.updateTitleEditText.setText(note.title)
        binding.updateContentEditText.setText(note.content)

        binding.updateSaveButton.setOnClickListener {
            val newTitle = binding.updateTitleEditText.text.toString()
            val newContent = binding.updateContentEditText.text.toString()
            val updatedNote = Note(noteID, newTitle, newContent)
            db.updateNote(updatedNote)
            finish()
            Toast.makeText(this,"Changes Saved",Toast.LENGTH_SHORT).show()
        }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}