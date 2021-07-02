package com.codingwolf.countryflags.ui.util

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.codingwolf.countryflags.R

object DialogUtil {

    fun displayOkDialog(context: Context, titleResId: Int, messageResId: Int) {
        AlertDialog
            .Builder(context)
            .setTitle(titleResId)
            .setMessage(messageResId)
            .setNeutralButton(R.string.all_ok) { _, _ -> }
            .show()
    }
}