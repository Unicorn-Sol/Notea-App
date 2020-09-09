package com.ezioapps.notenote.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ezioapps.notenote.R
import com.ezioapps.notenote.db.Note
import com.google.android.material.textview.MaterialTextView
import kotlinx.android.synthetic.main.notexml.view.*

class NotesAdapter(private val notes : List<Note>) :
    RecyclerView.Adapter<NotesAdapter.NViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NViewHolder {
       return NViewHolder(
           LayoutInflater.from(parent.context).inflate(R.layout.notexml, parent,false)
       )
    }

    override fun getItemCount(): Int = notes.size

    override fun onBindViewHolder(holder: NViewHolder, position: Int) {

        val note = notes[position]
        holder.view.title_tv.text = notes[position].title
        holder.view.body_tv.text = notes[position].body
        holder.view.setOnClickListener {

            val action = HomeFragmentDirections.actionHomeFragmentToAddNoteFragment(note)
            Navigation.findNavController(it).navigate(action)

        }
    }


    class NViewHolder(val view: View) : RecyclerView.ViewHolder(view)







}