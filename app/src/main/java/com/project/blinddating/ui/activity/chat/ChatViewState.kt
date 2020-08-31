package com.project.blinddating.ui.activity.chat

import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseUser
import com.project.blinddating.entity.ChatMsg
import com.project.blinddating.ui.activity.base.ViewState

class ChatViewState(
    var messagesLiveData: LiveData<List<ChatMsg>>? = null,
    var clearEdittextChat: Boolean = false,
    var firebaseUser: FirebaseUser? = null
): ViewState()
