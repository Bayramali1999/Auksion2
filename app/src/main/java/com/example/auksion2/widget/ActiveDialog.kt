package com.example.auksion2.widget

import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.auksion2.R
import com.example.auksion2.listener.OnDialogItemSelected

class ActiveDialog : androidx.fragment.app.DialogFragment() {

    private lateinit var title: String
    private lateinit var selectionArray: Array<String?>
    private lateinit var listener: OnDialogItemSelected
    private var ids: Int = -1
    private var checkedItem: Int = -1

    fun getData(
        title: String,
        list: Array<String?>,
        listener: OnDialogItemSelected
    ) {
        this.title = title
        selectionArray = list
        this.listener = listener
        this.checkedItem = checkedItem
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val activity = activity
        if (activity != null) {
            val dialog = AlertDialog.Builder(activity, R.style.MyAlertDialogStyle)

            dialog.setTitle(title)
                .setSingleChoiceItems(selectionArray, 0) { di, item ->
                    this.ids = item
                    this.listener.itemSelected(item)
                }
                .setPositiveButton("Ok") { di, _ ->
                    Toast.makeText(getActivity(), "item$ids", Toast.LENGTH_LONG).show()
                    di.dismiss()
                }
                .setNegativeButton("Bekor qilish") { di, id ->
                    di.dismiss()
                }

            return dialog.create()
        }
        return super.onCreateDialog(savedInstanceState)
    }
}

