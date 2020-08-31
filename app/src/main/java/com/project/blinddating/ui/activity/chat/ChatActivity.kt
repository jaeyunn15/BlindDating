package com.project.blinddating.ui.activity.chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project.blinddating.R
import com.project.blinddating.di.component.DaggerAppComponent
import com.project.blinddating.util.ActivityUtils
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class ChatActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var chatFragment: ChatFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        var fragment = supportFragmentManager.findFragmentById(R.id.contentFrame)

        if (fragment == null) {
            fragment = chatFragment
            ActivityUtils.addFragmentToActivity(
                supportFragmentManager,
                fragment,
                R.id.contentFrame
            )
        }
    }

    override fun onBackPressed() {
        chatFragment.saveRoomIdToPreferences(null)
        super.onBackPressed()
    }
}