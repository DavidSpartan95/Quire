package com.example.quire.dataBase.note

import android.graphics.Color

class Note(
    var title: String,
    var content: String,
    var date: String? = null,
    var favorite: Boolean = false,
    var tag: String,
    var color: Int = Color.YELLOW
) {
}