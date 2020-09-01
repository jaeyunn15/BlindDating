package com.project.blinddating.ui.activity.waiting

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.preference.PreferenceManager
import com.project.blinddating.R
import com.project.blinddating.di.scope.ActivityScoped
import com.project.blinddating.ui.activity.base.BaseFragment
import com.project.blinddating.util.AppConstants
import kotlinx.android.synthetic.main.fragment_waiting.*
import javax.inject.Inject

@ActivityScoped
class WaitingFragment @Inject constructor(
    override var viewModel: WaitingViewModel
) : BaseFragment<WaitingViewModel, WaitingViewState>(viewModel){

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        this.viewModel = ViewModelProviders.of(this, viewModelFactory).get(WaitingViewModel::class.java)

        setHasOptionsMenu(true)
    }

    override fun onBindViewModel() {
        super.onBindViewModel()
        //checkRoomIdPreference()
        viewModel.chkListData()
    }

    private fun checkRoomIdPreference(){
        val preferences = PreferenceManager.getDefaultSharedPreferences(activity)
        val roomId = preferences.getString(AppConstants.PREFERENCE_ROOMID, null)
        viewModel.checkRoomIdPreference(roomId)
    }

    override fun getLayoutResourceFile(): Int {
        return R.layout.fragment_waiting
    }

    override fun updateUI(state: WaitingViewState) {
        button_enter.isEnabled = state.enterBtnEnabled
        textview_error_enter.text = state.errorMessage
        state.newActivity?.start(state.clearActivityOnIntent, state.intentExtras)

        if (state.clearRoomTextfield) {
            state.clearRoomTextfield = false
            edittext_roomid.setText("")
        }
    }

    override fun attachClickListeners() {
        button_enter.setOnClickListener {
            viewModel.handleEnterButton(edittext_roomid.text.toString())
        }
        btn_logout.setOnClickListener {
            viewModel.handleSignOut()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.nav_signout -> {
                viewModel.handleSignOut()
                true
            }
            else -> false
        }
    }
}