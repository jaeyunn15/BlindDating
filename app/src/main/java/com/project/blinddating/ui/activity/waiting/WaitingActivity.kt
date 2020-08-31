package com.project.blinddating.ui.activity.waiting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project.blinddating.R
import com.project.blinddating.ui.activity.chat.ChatFragment
import com.project.blinddating.util.ActivityUtils
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class WaitingActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var waitingFragment: WaitingFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        var fragment = supportFragmentManager.findFragmentById(R.id.contentFrame)

        if (fragment == null) {
            fragment = waitingFragment
            ActivityUtils.addFragmentToActivity(
                supportFragmentManager,
                fragment,
                R.id.contentFrame
            )
        }
    }
}