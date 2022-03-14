package com.example.auksion2.widget

import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.auksion2.R
import com.example.auksion2.listener.OnDialogItemSelected

class ActiveDialog : androidx.fragment.app.DialogFragment() {

    private lateinit var title: String
    private lateinit var selectionArray: Array<String>
    private lateinit var listener: OnDialogItemSelected
    private var ids: Int = 0
    private var checkedItem: Int = 0

    fun getData(
        title: String,
        list: Array<String>,
        listener: OnDialogItemSelected
    ) {
        this.title = title
        selectionArray = list
        this.listener = listener
//        this.checkedItem = checkedItem
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val activity = activity
        if (activity != null) {
            val dialog = AlertDialog.Builder(activity, R.style.MyAlertDialogStyle)

            dialog.setTitle(title)
                .setSingleChoiceItems(selectionArray, checkedItem) { _, item ->
                    this.ids = item
                }
                .setPositiveButton("Ok") { di, _ ->
                    this.listener.itemSelected(ids)
                    ids = checkedItem
                    di.dismiss()
                }
                .setNegativeButton("Bekor qilish") { di, _ ->
                    di.dismiss()
                }

            return dialog.create()
        }
        return super.onCreateDialog(savedInstanceState)
    }
}

