package com.example.lokakuis.service.auth

import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.lokakuis.base.Constants
import com.example.lokakuis.base.service.SynchronousService
import org.koin.core.component.inject

class RemoveSavedToken : SynchronousService<Boolean>() {

    private val preference: SharedPreferences by inject()

    override fun run(): Boolean {
        preference.edit {
            remove(Constants.KEY_PROFILE)
            remove(Constants.KEY_TOKEN)
        }

        return true
    }

}