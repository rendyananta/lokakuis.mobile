package com.example.lokakuis.service.auth

import com.example.lokakuis.base.extensions.dispatchNow
import com.example.lokakuis.base.service.SynchronousService

class GetLoginStatus : SynchronousService<Boolean>() {

    override fun run(): Boolean {
        return dispatchNow(GetAuthorizationToken()) !== ""
    }
}
