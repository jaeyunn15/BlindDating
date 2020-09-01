package com.project.blinddating.ui.activity.profile

import com.project.blinddating.ui.activity.base.ViewState
import kotlin.reflect.KClass

class ProfileViewState (
    var submitEnabled : Boolean = true,
    newActivity : KClass<*>? = null,
    clearActivityOnIntent : Boolean  = false,
    var errorMsg : String = ""
) : ViewState(
    newActivity, clearActivityOnIntent
)