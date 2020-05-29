package com.sony.mynotes.ui

import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.sony.mynotes.R
import com.sony.mynotes.db.Note
import com.sony.mynotes.db.NoteDatabase
import kotlinx.android.synthetic.main.fragment_add_note.*

/**
 * A simple [Fragment] subclass.
 */
class AddNoteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_note, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        button_save.setOnClickListener {
            val noteTitle = edit_text_title.text.toString()
            val noteBody = edit_text_note.text.toString()

            if (noteTitle.isEmpty()) {
                edit_text_title.error = "Title Required"
                edit_text_title.requestFocus()
                return@setOnClickListener
            }
            if (noteBody.isEmpty()) {
                edit_text_note.error = "Note Required"
                edit_text_note.requestFocus()
                return@setOnClickListener
            }
            val note = Note(noteTitle, noteBody)
            saveNote(note)

        }
    }

    private fun saveNote(note: Note) {
        class SaveNotes : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg p0: Void?): Void? {
                NoteDatabase(activity!!).getNoteDao().addNote(note)
                return null


            }

            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)
                Toast.makeText(activity, "Note Saved Successfully", Toast.LENGTH_LONG).show()

                edit_text_title.text.clear()
                edit_text_note.text.clear()

            }

        }

        SaveNotes().execute()
    }

}
