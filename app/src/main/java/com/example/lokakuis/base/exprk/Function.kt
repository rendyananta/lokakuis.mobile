package com.example.lokakuis.base.exprk

import java.math.BigDecimal

abstract class Function {

    abstract fun call(arguments: List<BigDecimal>): BigDecimal

}