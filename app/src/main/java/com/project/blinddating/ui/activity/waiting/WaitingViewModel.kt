package com.project.blinddating.ui.activity.waiting

import com.project.blinddating.di.scope.AppScoped
import com.project.blinddating.helper.FirebaseHelper
import com.project.blinddating.ui.activity.base.BaseViewModel
import com.project.blinddating.ui.activity.chat.ChatActivity
import com.project.blinddating.util.AppConstants
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@AppScoped
class WaitingViewModel @Inject constructor(
    firebaseHelper: FirebaseHelper,
    viewState: WaitingViewState
): BaseViewModel<WaitingViewState>(firebaseHelper, viewState) {

    fun handleEnterButton(roomId: String) {
        if (!validate(roomId)) {
            updateUi()
            return
        }

        viewState.enterBtnEnabled = false
        updateUi()

        val errorHandler = CoroutineExceptionHandler { _, exception ->
            exception.printStackTrace()
            viewState.errorMessage = "Something went wrong. Please try again"
            viewState.enterBtnEnabled = true
            updateUi()
        }

        networkScope.launch(errorHandler) {
            firebaseHelper.addUserToChat(roomId)
            firebaseHelper.addFirebase(roomId)
            launchRoomActivity(roomId)
        }

    }

    private fun launchRoomActivity(roomId: String) {
        viewState.enterBtnEnabled = true
        viewState.clearRoomTextfield = true
        viewState.newActivity = ChatActivity::class
        viewState.clearActivityOnIntent = false
        viewState.intentExtras.putString(AppConstants.INTENT_EXTRA_ROOMID, roomId)
        updateUi()
    }

    private fun validate(roomId: String): Boolean {

        if (roomId.isEmpty()) {
            viewState.errorMessage = "Please enter a Room ID"
            return false
        }

        viewState.errorMessage = ""
        return true
    }

    fun checkRoomIdPreference(roomId: String?) {
        roomId?.let { launchRoomActivity(it) }
    }

    fun chkListData(){
        firebaseHelper.ex()
    }
}