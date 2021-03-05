package com.bbeniful.betworklistener.listener

import android.app.Activity
import android.app.Service
import android.content.BroadcastReceiver
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.LocalServerSocket
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.bbeniful.betworklistener.exceptions.NotSupportedTypeException
import kotlin.reflect.KClass

object BetworkRegister {

    fun registerBetwork(type: Any) {
        when (type) {
            is Activity -> {
                type.registerReceiver(
                    BetworkChecker(),
                    IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
                )
            }
            is Fragment -> {
                LocalBroadcastManager.getInstance(type.requireContext()).registerReceiver(
                    BetworkChecker(),
                    IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
                )
            }
            is Service -> {
                LocalBroadcastManager.getInstance(type.applicationContext).registerReceiver(
                    BetworkChecker(),
                    IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
                )
            }
            else -> {
                throw NotSupportedTypeException()
            }
        }
    }

}

