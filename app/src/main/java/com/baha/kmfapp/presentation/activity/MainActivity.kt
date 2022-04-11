package com.baha.kmfapp.presentation.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.baha.kmfapp.databinding.ActivityMainBinding
import com.baha.kmfapp.presentation.fragment.ShowInfoDialog

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.llRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
        binding.llInfo.setOnClickListener {
            ShowInfoDialog().show(supportFragmentManager,null)
        }

    }
}