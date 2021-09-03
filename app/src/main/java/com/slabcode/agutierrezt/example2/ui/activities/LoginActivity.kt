package com.slabcode.agutierrezt.example2.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.slabcode.agutierrezt.example2.R
import com.slabcode.agutierrezt.example2.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}