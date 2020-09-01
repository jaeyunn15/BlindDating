package com.project.blinddating.ui.activity.chat

import com.project.blinddating.di.scope.AppScoped
import com.project.blinddating.helper.FirebaseHelper
import com.project.blinddating.ui.activity.base.BaseViewModel
import com.project.blinddating.ui.activity.login.LoginActivity
import com.project.blinddating.ui.activity.waiting.WaitingActivity
import javax.inject.Inject


@AppScoped
class ChatViewModel @Inject constructor(
    firebaseHelper: FirebaseHelper,
    viewState: ChatViewState
): BaseViewModel<ChatViewState>(firebaseHelper, viewState) {

    init {
        viewState.firebaseUser = firebaseHelper.user()
    }

    fun setRoomId(roomId: String) {
        firebaseHelper.chatId = roomId
        getChatMessages()
    }

    fun handleSendButton(message: String) {
        sendMessage(message)
        viewState.clearEdittextChat = true
        updateUi()
    }

    private fun sendMessage(message: String) {
        firebaseHelper.sendChatMsg(message)
        firebaseHelper.ex()
    }

    private fun getChatMessages() {
        firebaseHelper.observeChatMsg()
        viewState.messagesLiveData = firebaseHelper.chatMSGLiveData
        updateUi()
    }

}