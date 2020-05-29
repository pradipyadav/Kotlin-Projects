package com.sony.mynotes.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.sony.mynotes.R
import com.sony.mynotes.db.Note
import com.sony.mynotes.db.NoteDatabase
import kotlinx.android.synthetic.main.fragment_add_note.*
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 */
class AddNoteFragment : BaseFragment() {

    private var note: Note? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_note, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.let {
            note = AddNoteFragmentArgs.fromBundle(it).note
            edit_text_title.setText(note?.title)
            edit_text_note.setText(note?.note)
        }

        button_save.setOnClickListener { view ->
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

            launch {


                context?.let {

                    val mNote = Note(noteTitle, noteBody)

                    if (note == null) {

                        NoteDatabase(it).getNoteDao().addNote(mNote)
                        it.toast("Note Saved!!")
                    } else {

                        mNote.id = note!!.id
                        NoteDatabase(it).getNoteDao().updateNote(mNote)
                        it.toast("Note Update!!")

                    }
                    val action = AddNoteFragmentDirections.actionSaveNote()
                    Navigation.findNavController(view).navigate(action)


                }

            }


        }
    }

    private fun deleteNote() {

        AlertDialog.Builder(context).apply {
            setTitle("Are you sure?")
            setMessage("You cannot undo this opration")
            setPositiveButton("Yes") { _, _ ->
                launch {
                    NoteDatabase(context).getNoteDao().deleteNote(note!!)

                    val action = AddNoteFragmentDirections.actionSaveNote()
                    Navigation.findNavController(view!!).navigate(action)


                }
            }
            setNegativeButton("No") { _, _ ->
            }

        }.create().show()

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.delete -> if (note != null) deleteNote() else context?.toast("Cannote Delete")

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menu, menu)
    }

/*

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

*/

}

