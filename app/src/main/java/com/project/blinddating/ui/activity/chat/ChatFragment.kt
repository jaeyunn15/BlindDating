package com.project.blinddating.ui.activity.chat

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.blinddating.R
import com.project.blinddating.di.scope.ActivityScoped
import com.project.blinddating.entity.ChatMsg
import com.project.blinddating.ui.activity.base.BaseFragment
import com.project.blinddating.ui.activity.login.LoginActivity
import com.project.blinddating.ui.activity.waiting.WaitingActivity
import com.project.blinddating.util.AppConstants
import kotlinx.android.synthetic.main.fragment_chat.*
import javax.inject.Inject

@ActivityScoped
class ChatFragment  @Inject constructor(override var viewModel: ChatViewModel): BaseFragment<ChatViewModel, ChatViewState>(viewModel) {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ChatViewModel::class.java)

        activity?.intent?.extras?.let {
            val roomId = it[AppConstants.INTENT_EXTRA_ROOMID] as String
            viewModel.setRoomId(roomId)
            saveRoomIdToPreferences(roomId)
            Log.d("룸아디","$roomId")
        } ?: run {
            WaitingActivity::class.start(true)
        }
    }

    override fun getLayoutResourceFile(): Int {
        return R.layout.fragment_chat
    }

    override fun updateUI(state: ChatViewState) {

        if (state.clearEdittextChat) {
            edittext_chat.setText("")
            state.clearEdittextChat = false
        }

        state.messagesLiveData?.observe(viewLifecycleOwner, Observer{
            setListAdapter(it, state.firebaseUser!!.uid)
        })


    }

    private fun setListAdapter(messages: List<ChatMsg>, uid: String) {
        val adapter = ChatAdapter(messages, uid)
        list_chat.layoutManager = LinearLayoutManager(activity)
        list_chat.adapter = adapter
    }

    override fun attachClickListeners() {
        button_send.setOnClickListener {
            viewModel.handleSendButton(edittext_chat.text.toString())
        }
    }

    fun saveRoomIdToPreferences(roomId: String?) {
        val preferences = PreferenceManager.getDefaultSharedPreferences(activity)

        preferences.edit()
            .putString(AppConstants.PREFERENCE_ROOMID, roomId)
            .apply()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            R.id.nav_signout -> {
                saveRoomIdToPreferences(null)
                viewModel.handleSignOut()
                true
            }
            else -> false


        }
    }
}