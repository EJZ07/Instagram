package com.example.fake_insta

import android.text.method.DateTimeKeyListener
import android.widget.TextView
import com.parse.ParseClassName
import com.parse.ParseFile
import com.parse.ParseObject
import com.parse.ParseUser
import java.util.Date

@ParseClassName("Post")
class Post : ParseObject() {

    fun getDescription(): String? {
        return getString(KEY_DESCRIPTION)
    }

    fun setDescription(description: String){
        put(KEY_DESCRIPTION, description)
    }

    fun getImage(): ParseFile? {
        return getParseFile(KEY_IMAGE)
    }

    fun setImage(parsefile: ParseFile) {
        put(KEY_IMAGE, parsefile)
    }

    fun getpImage(): ParseFile? {
        return getParseFile(KEY_PROFILE)
    }

    fun setpImage(parsefile: ParseFile) {
        put(KEY_PROFILE, parsefile)
    }

    fun getUser(): ParseUser? {
        return getParseUser(KEY_USER)
    }

    fun setUser(user: ParseUser){
        put(KEY_USER, user)
    }

    fun getPosted(): String? {
        return getString(KEY_POSTED)
    }

    fun setPosted(postedDate: String){
        put(KEY_POSTED, postedDate)
    }

    companion object {
        const val KEY_DESCRIPTION = "description"
        const val KEY_IMAGE = "image"
        const val KEY_USER = "user"
        const val KEY_PROFILE = "ProfilePic"
        const val KEY_POSTED = "createdAt"
    }
}