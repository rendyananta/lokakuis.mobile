package com.example.lokakuis.base.extensions

import com.example.lokakuis.base.service.AsynchronousService
import com.example.lokakuis.base.service.SynchronousService

suspend fun <T> dispatch(svc: AsynchronousService<T>): T = svc.runAsync()

fun <T> dispatchNow(svc: SynchronousService<T>): T = svc.run()
