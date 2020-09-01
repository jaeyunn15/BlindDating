package com.project.blinddating.ui.activity.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.project.blinddating.R
import com.project.blinddating.di.scope.ActivityScoped
import com.project.blinddating.ui.activity.base.BaseFragment
import com.project.blinddating.ui.activity.register.RegisterViewModel
import com.project.blinddating.ui.activity.register.RegisterViewState
import com.project.blinddating.util.AppConstants
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_register.*
import javax.inject.Inject

@ActivityScoped
class ProfileFragment @Inject constructor(override var viewModel: ProfileViewModel)
    : BaseFragment<ProfileViewModel, ProfileViewState>(viewModel){

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ProfileViewModel::class.java)

        activity?.intent?.extras?.let {
            val email = it[AppConstants.REGISTER_USEREMAIL] as String
            val passwd = it[AppConstants.REGISTER_USERPASSWORD] as String
            viewModel.setUpEmailPasswd(email,passwd)
        }
    }

    override fun updateUI(state: ProfileViewState) {
        button_join.isEnabled = state.submitEnabled
        state.newActivity?.start(state.clearActivityOnIntent)
        textview_error_profile.text = state.errorMsg
    }

    override fun getLayoutResourceFile(): Int {
        return R.layout.fragment_profile
    }

    override fun attachClickListeners() {
        button_join.setOnClickListener {
            viewModel.handleSubmitJoinButtonClicked(
                edittext_id.text.toString(),
                edittext_name.text.toString(),
                edittext_gender.text.toString(),
                edittext_age.text.toString(),
                edittext_address.text.toString()
            )
        }
    }
}