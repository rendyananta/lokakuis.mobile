package com.example.lokakuis.base.service

abstract class SynchronousService<T> : Service {
    abstract fun run(): T
}
