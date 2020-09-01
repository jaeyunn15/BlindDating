package com.project.blinddating.helper

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.Timestamp
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.project.blinddating.entity.ChatMsg
import com.project.blinddating.entity.Example
import com.project.blinddating.entity.User
import kotlinx.coroutines.tasks.await
import java.util.*

class FirebaseHelper {

    val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    var chatId = ""
    val chatMSGLiveData = MutableLiveData<List<ChatMsg>>()

    fun user() : FirebaseUser? = auth.currentUser

    suspend fun login(email:String, password:String) : AuthResult {
        return auth.signInWithEmailAndPassword(email,password).await()
    }

    suspend fun register(email: String, password: String) : AuthResult {
        return auth.createUserWithEmailAndPassword(email, password).await()
    }

    suspend fun addProfile(user : User) : DocumentReference{
        return firestore.collection("User")
            .document(user.user_age)
            .collection("profile")
            .add(mapOf(
                Pair("id", user.user_id),
                Pair("name", user.user_name),
                Pair("gender", user.user_gender),
                Pair("age", user.user_age),
                Pair("address", user.user_address),
                Pair("email", user.user_id),
            ))
            .await()
    }

    suspend fun addUserToChat(chatId : String) : DocumentReference {
        return firestore.collection("chats")
            .document(chatId)
            .collection("users")
            .add(mapOf(
                Pair("id", user()?.uid),
                Pair("name", user()?.displayName),
                Pair("image", user()?.photoUrl)
            ))
            .await()
    }

    suspend fun addFirebase(chatId: String) : DocumentReference {
        return firestore.collection("chatList")
            .document("chatRoom")
            .collection(chatId)
            .add(mapOf(
                Pair("roomId", chatId),
                Pair("email", user()?.email)
            ))
            .await()
    }


    fun sendChatMsg(msg: String) {
        firestore.collection("chats")
            .document(chatId)
            .collection("messages")
            .add(mapOf(
                Pair("text", msg),
                Pair("user", user()?.uid),
                Pair("timestamp", com.google.firebase.Timestamp.now())
        ))
    }

    fun observeChatMsg(){
        firestore.collection("chats")
            .document(chatId)
            .collection("messages")
            .orderBy("timestamp")
            .addSnapshotListener { value, error ->
                if (error != null){
                    error.printStackTrace()
                    return@addSnapshotListener
                }

                val messages = value?.documents?.map {
                    ChatMsg(
                        it["text"] as String,
                        it["user"] as String,
                        (it["timestamp"]) as Timestamp
                    )
                }
                messages.let { chatMSGLiveData.postValue(messages) }
            }
    }

    fun ex(){
        Log.d("EX :: ","진입!")
        firestore.collection("chats")
            .addSnapshotListener { value, error ->
                if (error != null){
                    error.printStackTrace()
                    return@addSnapshotListener
                }

                val ex = value?.documents?.map {
                    Example(
                        it["num"] as String
                    )
                }
                ex.let {
                    Log.d("성공 ", "$it")
                }


            }
    }
}