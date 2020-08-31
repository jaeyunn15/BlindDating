package com.project.blinddating.ui.activity.waiting

import com.project.blinddating.ui.activity.base.ViewState

class WaitingViewState(
    var errorMessage: String = "",
    var enterBtnEnabled: Boolean = true,
    var clearRoomTextfield: Boolean = false
): ViewState()