package com.project.blinddating.entity

import com.google.firebase.Timestamp
import java.util.*

data class ChatMsg(
    val text : String,
    val user : String,
    val timestamp: Timestamp
)