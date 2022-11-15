package com.example.marmitech.extension

import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

fun Context.showDialog(message: String, title: String = "") {
    var alertDialog: AlertDialog? = null
    try {
        val builder = AlertDialog.Builder(this)
        if (title != ""){
            builder.setTitle(title)
        }
        builder.setMessage(message)
            .setPositiveButton("OK") { _, _ ->

            }

        alertDialog = builder.create()
        alertDialog.show()
    } catch (e: Exception) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }

    alertDialog?.getButton(DialogInterface.BUTTON_POSITIVE)?.setTextColor(Color.BLUE)
}