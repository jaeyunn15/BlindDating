package com.project.blinddating.ui.activity.profile

import android.os.Bundle
import com.project.blinddating.R
import com.project.blinddating.ui.activity.register.RegisterFragment
import com.project.blinddating.util.ActivityUtils
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class ProfileActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var profileFragment: ProfileFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        var fragment = supportFragmentManager.findFragmentById(R.id.contentFrame)
        if (fragment == null){
            fragment = profileFragment
            ActivityUtils.addFragmentToActivity(
                supportFragmentManager,
                fragment,
                R.id.contentFrame
            )
        }
    }
}