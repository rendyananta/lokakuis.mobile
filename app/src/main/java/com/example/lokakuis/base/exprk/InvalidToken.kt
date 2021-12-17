package com.example.lokakuis.base.exprk

internal fun invalidToken(c: Char) {
    throw ExpressionException("Invalid token '$c'")
}