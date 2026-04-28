package com.example.lb9

import android.text.Editable
import android.text.TextWatcher

class SimpleTextWatcher(private val afterTextChanged: () -> Unit) : TextWatcher {

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

    override fun afterTextChanged(s: Editable?) {
        afterTextChanged.invoke()
    }
}