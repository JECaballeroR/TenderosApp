package com.slabcode.agutierrezt.example2.utils

import android.util.Patterns

fun CharSequence?.isValidEmail() = !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()