package com.project.blinddating.ui.activity.chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.blinddating.R
import com.project.blinddating.entity.ChatMsg
import kotlinx.android.synthetic.main.list_item_chat.view.*

class ChatAdapter(val chatMessages: List<ChatMsg>, val uid: String)
    : RecyclerView.Adapter<ChatAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return chatMessages.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val chatMessage = chatMessages[position]

        if (chatMessage.user == uid) { //내 메세지라면
            holder.itemView.textview_chat_received.text = chatMessage.text
            holder.itemView.textview_chat_sent.visibility = View.GONE
        } else {
            holder.itemView.textview_chat_sent.text = chatMessage.text
            holder.itemView.textview_chat_received.visibility = View.GONE

        }
    }


    class ViewHolder(inflater: LayoutInflater, parent: ViewGroup): RecyclerView.ViewHolder(
        inflater.inflate(R.layout.list_item_chat, parent, false)
    ) {

        private var chatTextSent: TextView? = null
        private var chatTextReceived: TextView? = null

        init {
            chatTextSent = itemView.findViewById(R.id.textview_chat_sent)
            chatTextReceived = itemView.findViewById(R.id.textview_chat_received)
        }

    }

}