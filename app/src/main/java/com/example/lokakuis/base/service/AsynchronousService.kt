package com.example.lokakuis.base.service

abstract class AsynchronousService<T> : Service {
    abstract suspend fun runAsync(): T
}
