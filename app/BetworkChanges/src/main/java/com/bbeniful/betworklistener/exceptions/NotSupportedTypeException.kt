package com.bbeniful.betworklistener.exceptions

import java.lang.Exception

class NotSupportedTypeException: Exception() {

    override val message: String?
        get() = "This type is currently not supported"
}