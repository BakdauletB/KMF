package com.baha.kmfapp.presentation.activity

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.ViewModelProvider
import com.baha.kmfapp.*
import com.baha.kmfapp.databinding.ActivityRegistrationBinding
import com.baha.kmfapp.presentation.fragment.LoadingDialog
import com.baha.kmfapp.presentation.fragment.StatusBottomSheet
import kz.edu.kmf.data.model.User
import java.util.regex.Matcher
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity(){
    private lateinit var binding: ActivityRegistrationBinding

    private val viewModel : RegistrationViewModel by lazy {
        ViewModelProvider(this).get(RegistrationViewModel::class.java)
    }
    private lateinit var loadingDialog: LoadingDialog



    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = ContextCompat.getColor(this, R.color.green)
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = false
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadingDialog = LoadingDialog()
        binding.statusButton.setOnClickListener {
            showStatusFragment()
        }
        binding.authButton.setOnClickListener {
            if (!validate(binding.emailField.text.toString())) {
                Toast.makeText(
                    applicationContext,
                    "Недопустимый адрес электронной почты",
                    Toast.LENGTH_LONG
                ).show()
            } else if (binding.numberField.text!!.length < 9) {
                Toast.makeText(
                    applicationContext,
                    "Недопустимый формат номера",
                    Toast.LENGTH_LONG
                ).show()
            } else if (binding.loginField.text!!.isEmpty() || binding.nameField.text!!.isEmpty() || binding.passwordField.text!!.isEmpty() || binding.statusField.text!!.isEmpty()) {
                Toast.makeText(
                    applicationContext,
                    "Заполните все поля",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                loadingDialog.show(supportFragmentManager, null)
                registerUser(
                    User(
                        0,
                        binding.loginField.text.toString(),
                        binding.nameField.text.toString().split(" ")[0],
                        binding.nameField.text.toString().split(" ")[1],
                        binding.emailField.text.toString(),
                        binding.passwordField.text.toString(),
                        binding.numberField.text.toString(),
                        binding.statusField.text.toString().toInt()
                    )
                )
            }
        }
    }

    private val regexEmail: Pattern =
        Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE)

    private fun validate(emailStr: String?): Boolean {
        val matcher: Matcher = regexEmail.matcher(emailStr)
        return matcher.find()
    }

    private fun registerUser(user: User) {
        viewModel.registerUser(user).observe(this, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        loadingDialog.dismiss()
                        Toast.makeText(
                            applicationContext,
                            "Пользователь успешно создан",
                            Toast.LENGTH_LONG
                        ).show()
                        binding.loginField.text!!.clear()
                        binding.nameField.text!!.clear()
                        binding.nameField.text!!.clear()
                        binding.emailField.text!!.clear()
                        binding.passwordField.text!!.clear()
                        binding.numberField.text!!.clear()
//                        binding.statusButton.text.clear()
                        binding.statusField.text!!.clear()
                        val view: View? = this.currentFocus
                        if (view != null) {
                            val imm: InputMethodManager =
                                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                            imm.hideSoftInputFromWindow(view.windowToken, 0)
                        }
                    }
                    Status.ERROR -> {
                        Toast.makeText(applicationContext, "Ошибка", Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
//                        Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
    }
    private fun showStatusFragment() {
        val bottomSheetFragment = StatusBottomSheet()
        bottomSheetFragment.show(supportFragmentManager, StatusBottomSheet.TAG)

    }
}