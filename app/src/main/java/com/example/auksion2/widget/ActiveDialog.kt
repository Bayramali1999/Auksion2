package com.example.auksion2.widget

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class ActiveDialog : DialogFragment() {
    private lateinit var title: String
    private var selectionArray = arrayOf<String>()

    fun getData(title: String, list: Array<String>) {
        this.title = title
        selectionArray = list

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activity = activity
        if (activity != null) {
            val dialog = AlertDialog.Builder(activity)

            dialog.setTitle(title)
                .setSingleChoiceItems(selectionArray, 0) { _, item -> }
                .setPositiveButton("Ok") { listener, id -> }
                .setNegativeButton("Bekor qilish") { listener, id -> }
                .create()

            dialog.create()
        }
    }

}

