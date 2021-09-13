package es.jco.demo.presentation.common

import android.content.Context
import android.content.DialogInterface
import android.util.Log
import androidx.appcompat.app.AlertDialog


fun Log.e(tag: String, msg: String?) = Log.e(tag, msg.let { msg ?: "Cause not found"})

fun Context.showErrorDialog(message: Int, acceptButton: Int,  acceptListener: DialogInterface.OnClickListener) = AlertDialog.Builder(this)
    .setMessage(message)
    .setPositiveButton(acceptButton, acceptListener)
    .setCancelable(false)
    .create()
    .show()
