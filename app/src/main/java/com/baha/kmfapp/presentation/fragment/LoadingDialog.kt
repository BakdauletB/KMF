package com.baha.kmfapp.presentation.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.baha.kmfapp.databinding.LoaderViewBinding

class LoadingDialog : DialogFragment() {
    private lateinit var binding : LoaderViewBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = LoaderViewBinding.inflate(layoutInflater)
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
        builder.setView(binding.root)
        isCancelable = false
        return builder.create()
    }

    override fun onStart() {
        super.onStart()
        this@LoadingDialog.dialog?.window
            ?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}