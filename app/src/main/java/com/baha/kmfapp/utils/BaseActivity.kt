package com.baha.kmfapp.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.baha.kmfapp.NoInternetException
import com.baha.kmfapp.R
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {

    private var alert: AlertDialog? = null

    fun showLoader(parentView: ViewGroup) {
        if (!this.isFinishing) {
            closeKeyboard()
            val isLoading = parentView.findViewById<ViewGroup>(R.id.loader_view) != null
            if (!isLoading) {
                val loaderView =
                    LayoutInflater.from(this).inflate(R.layout.loader_view, parentView, false)
                parentView.addView(loaderView)
            }
        }
    }

    fun hideLoader(parentView: ViewGroup) {
        if (!this.isFinishing) {
            if (parentView.findViewById<ViewGroup>(R.id.loader_view) != null)
                parentView.removeView(parentView.findViewById(R.id.loader_view))
        }
    }

    fun closeKeyboard() {
        currentFocus?.let {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm!!.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }

    fun showTextAlert(message: String) {
        if (!this.isFinishing) {
            val alertBuilder = AlertDialog.Builder(this)
            alertBuilder.setMessage(message)
            alertBuilder.setPositiveButton("OK", null)
            alertBuilder.setCancelable(true)
            alert = alertBuilder.create()
            alert!!.show()
        }
    }

    fun showErrorMessageBy(error: Throwable?) {
        if (!isFinishing) {
            error?.let {
                when (error) {
                    is SocketTimeoutException, is TimeoutException, is ConnectException, is HttpException -> {
                        showTextAlert("???????????? ??????????????, ???????????????????? ??????????????")
                        return
                    }
                    is UnknownHostException, is NoInternetException -> {
                        showTextAlert("?????? ???????????????????? ?? ????????????????????")
                        return
                    }
                    else -> {
                        showTextAlert("?????????????????? ????????????")
                        return
                    }
                }
            }
        }
    }

    fun showUltimateAlert(
        title: String? = null, message: String? = null,
        positiveBtnName: String? = null, negativeBtnName: String? = null,
        positiveBtnHandler: DialogInterface.OnClickListener? = null,
        negativeBtnHandler: DialogInterface.OnClickListener? = null
    ) {
        if (!this.isFinishing) {
            val alertBuilder = AlertDialog.Builder(this)
            alertBuilder.setTitle(title)
            alertBuilder.setMessage(message)
            positiveBtnName?.let {
                alertBuilder.setPositiveButton(
                    positiveBtnName,
                    positiveBtnHandler
                )
            }
            negativeBtnName?.let {
                alertBuilder.setNegativeButton(
                    negativeBtnName,
                    negativeBtnHandler
                )
            }
            alertBuilder.setCancelable(true)
            alertBuilder.setOnCancelListener { negativeBtnHandler?.onClick(alert, 0) }
            alert = alertBuilder.create()
            alert!!.show()
        }
    }


    fun safeOpenBrowser(url: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(this, "???????????????????? ?????????????? ??????????????", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onStop() {
        super.onStop()
        alert?.dismiss()
    }
}