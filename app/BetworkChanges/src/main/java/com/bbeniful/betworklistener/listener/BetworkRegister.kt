package com.bbeniful.betworklistener.listener

import android.app.Activity
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.LocalServerSocket
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import kotlin.reflect.KClass

object BetworkRegister {

    fun registerBetwork(type: Any) {
        if (type is Activity) {
            type.registerReceiver(
                BetworkChecker(),
                IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
            )
        }

        if (type is Fragment) {
            LocalBroadcastManager.getInstance(type.requireContext()).registerReceiver(
                BetworkChecker(),
                IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
            )

        }
    }

}

