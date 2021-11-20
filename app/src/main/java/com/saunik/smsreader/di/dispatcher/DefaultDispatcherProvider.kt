package com.go.social._dispatcher

import kotlinx.coroutines.Dispatchers

class DefaultDispatcherProvider : DispatcherProvider {
    override fun main() = Dispatchers.Main

    override fun default() = Dispatchers.Default

    override fun io() = Dispatchers.IO

    override fun unconfined() = Dispatchers.Unconfined
}
