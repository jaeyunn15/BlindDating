package com.project.blinddating.ui.activity.register

import com.project.blinddating.ui.activity.base.ViewState
import kotlin.reflect.KClass

class RegisterViewState (
    var submitEnabled : Boolean = true,
    newActivity : KClass<*>? = null,
    clearActivityOnIntent : Boolean  = false,
    var errorMsg : String = ""
) : ViewState(
    newActivity, clearActivityOnIntent
)