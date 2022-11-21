package com.example.marmitech.extension

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

fun Activity.showDialog(message: String, title: String = "", closeWindow: Boolean = false) {
    var alertDialog: AlertDialog? = null
    try {
        val builder = AlertDialog.Builder(this)
        if (title != "") {
            builder.setTitle(title)
        }
        builder.setMessage(message)
            .setPositiveButton("OK") { _, _ ->
                if(closeWindow) {
                    finish()
                }
            }

        alertDialog = builder.create()
        alertDialog.show()
    } catch (e: Exception) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }

    alertDialog?.getButton(DialogInterface.BUTTON_POSITIVE)?.setTextColor(Color.BLUE)
}

fun Context.showToast(message: String) {
    Toast.makeText(
        this,
        message,
        Toast.LENGTH_LONG
    ).show()
}