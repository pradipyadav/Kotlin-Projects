package com.sony.mynotes.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.sony.mynotes.R
import com.sony.mynotes.db.Note
import kotlinx.android.synthetic.main.note_layout.view.*

class NotesAdapter(val notes:List<Note>):RecyclerView.Adapter<NotesAdapter.NotesAdapterViewHolder>() {

    class NotesAdapterViewHolder(val view: View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesAdapterViewHolder {
        return NotesAdapterViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.note_layout,parent,false)
        )
    }

    override fun getItemCount()=notes.size

    override fun onBindViewHolder(holder: NotesAdapterViewHolder, position: Int) {
        holder.view.text_view_title.text=notes[position].title
        holder.view.text_view_note.text=notes[position].note

        holder.view.setOnClickListener {
            val action=HomeFragmentDirections.actionAddNote()
            action.note=notes[position]
            Navigation.findNavController(it).navigate(action)
        }
    }


}