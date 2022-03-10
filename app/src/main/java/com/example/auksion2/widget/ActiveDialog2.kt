package com.example.auksion2.widget

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

import com.example.auksion2.listener.OnDialogItemSelected

class ActiveDialog2 : DialogFragment() {
    private lateinit var title: String
    private var selectionArray = arrayOf<String>()
    private lateinit var listener: OnDialogItemSelected
    private var ids: Int = 0

    fun getData(title: String, list: Array<String>, listener: OnDialogItemSelected) {
        this.title = title
        selectionArray = list
        this.listener = listener
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activity = activity
        if (activity != null) {
            val dialog = AlertDialog.Builder(activity)

            dialog.setTitle(title)
                .setSingleChoiceItems(selectionArray, 0) { _, item ->
                    ids = item
                }
                .setPositiveButton("Ok") { listener, id ->
                    this.listener.itemSelected(ids)
                }
                .setNegativeButton("Bekor qilish") { listener, id -> }
                .create()

            dialog.create()
        }
    }

}

