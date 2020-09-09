package com.ezioapps.notenote.ui

import android.app.AlertDialog
import android.os.AsyncTask
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.ezioapps.notenote.R
import com.ezioapps.notenote.db.Note
import com.ezioapps.notenote.db.NoteDatabase
import kotlinx.android.synthetic.main.fragment_add_note.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


class AddNoteFragment : BaseFragment() {


    private var note : Note? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.add_note_fragment_menu, menu)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)

        return inflater.inflate(R.layout.fragment_add_note, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


       arguments?.let {
           note = AddNoteFragmentArgs.fromBundle(it).note

           etName.setText(note?.title)
           etBody.setText(note?.body)
       }


        doneNoteFab.setOnClickListener {

            val noteTitle = etName.text.toString().trim()
            val noteBody = etBody.text.toString().trim()

            if (noteTitle.isNullOrEmpty()){
                etName.error = "Title is required"
                etName.requestFocus()
                return@setOnClickListener
            }


            if (noteBody.isNullOrEmpty()){
                etBody.error = "Body is also needed bro"
                etBody.requestFocus()
                return@setOnClickListener
            }


           launch {

               context?.let {


                   val mNote = Note(0, noteTitle, noteBody)

                   if (note == null){
                       NoteDatabase.getAppDataBase(it)?.getNoteDao()?.addNote(mNote)
                       it.toast("hey note saved")
                   }
                   else{
                       mNote.id = note!!.id
                       NoteDatabase.getAppDataBase(it)?.getNoteDao()?.updateNote(mNote)
                       it.toast("hey note updated")
                   }



               }
           }

            it.findNavController().navigate(R.id.action_addNoteFragment_to_homeFragment)
            
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.deleteMenuItem -> if (note != null) deleteNote() else context?.toast("Cannot Delete")
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteNote() {
        AlertDialog.Builder(context).apply {
            setTitle("Are You Damn Fucking Sure?")
            setMessage("You cannot undo this action!!")
            setPositiveButton("YES"){ _,_ ->

                    findNavController().navigate(R.id.action_addNoteFragment_to_homeFragment)

                launch {   NoteDatabase.getAppDataBase(context)?.getNoteDao()?.deleteNote(note!!)

            } }
            setNegativeButton("NOO..."){dialog, _ ->
                dialog.dismiss()
            }
        }.show()
    }

}