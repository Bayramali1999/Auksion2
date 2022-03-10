package com.example.auksion2.widget

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
<<<<<<< HEAD
import com.example.auksion2.listener.OnDialogItemSelected
=======
>>>>>>> origin/filter_activity

class ActiveDialog : DialogFragment() {
    private lateinit var title: String
    private var selectionArray = arrayOf<String>()
<<<<<<< HEAD
    private lateinit var listener: OnDialogItemSelected
    private var ids: Int = 0

    fun getData(title: String, list: Array<String>, listener: OnDialogItemSelected) {
        this.title = title
        selectionArray = list
        this.listener = listener
=======

    fun getData(title: String, list: Array<String>) {
        this.title = title
        selectionArray = list

>>>>>>> origin/filter_activity
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activity = activity
        if (activity != null) {
            val dialog = AlertDialog.Builder(activity)

            dialog.setTitle(title)
<<<<<<< HEAD
                .setSingleChoiceItems(selectionArray, 0) { _, item ->
                    ids = item
                }
                .setPositiveButton("Ok") { listener, id ->
                    this.listener.itemSelected(ids)
                }
=======
                .setSingleChoiceItems(selectionArray, 0) { _, item -> }
                .setPositiveButton("Ok") { listener, id -> }
>>>>>>> origin/filter_activity
                .setNegativeButton("Bekor qilish") { listener, id -> }
                .create()

            dialog.create()
        }
    }

}

