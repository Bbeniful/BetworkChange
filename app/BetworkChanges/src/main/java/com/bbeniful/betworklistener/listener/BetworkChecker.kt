package com.bbeniful.betworklistener.listener

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.MutableLiveData
import com.bbeniful.betworklistener.enums.NetworkType


@Suppress("SpellCheckingInspection")
class BetworkChecker : BroadcastReceiver() {

    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    override fun onReceive(context: Context?, intent: Intent?) {

        if (connectivityReceiverListener != null) {
            connectivityReceiverListener!!.networkConnectionChanged(
                isNetworkAvailable(
                    context!!
                )
            )
            connectivityReceiverListener!!.getNetworkType(type)
        }

        betWorkLiveCheck.postValue(
            isNetworkAvailable(
                context!!
            )
        )
    }

    @Suppress("DEPRECATION")
    private fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            type = NetworkType.NO_NETWORK
            betWorkLiveType.postValue(type)
            val nw = connectivityManager.activeNetwork ?: return false
            val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
            return when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    type = NetworkType.WIFI
                    betWorkLiveType.postValue(type)
                    true
                }
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    type = NetworkType.INTERNET
                    betWorkLiveType.postValue(type)
                    true
                }
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                    type = NetworkType.INTERNET
                    betWorkLiveType.postValue(type)
                    true
                }
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> {
                    type = NetworkType.BLUETOOTH
                    betWorkLiveType.postValue(type)
                    true
                }
                else -> {
                    type = NetworkType.NONE
                    betWorkLiveType.postValue(type)
                    false
                }
            }
        } else {
            val nwInfo = connectivityManager.activeNetworkInfo ?: return false
            return nwInfo.isConnected
        }
    }

    interface ConnectivityReceiverListener {
        fun networkConnectionChanged(isConnected: Boolean)
        fun getNetworkType(type: NetworkType)
    }

    companion object {
        private var type: NetworkType = NetworkType.NONE
        private var connectivityReceiverListener: ConnectivityReceiverListener? = null
        private var betWorkLiveCheck = MutableLiveData<Boolean>()
        private var betWorkLiveType = MutableLiveData<NetworkType>()

        @JvmStatic
        fun getNetworkStateAsLiveData() = this.betWorkLiveCheck

        @JvmStatic
        fun getNetworkTypeAsLiveData() = this.betWorkLiveType

        @JvmStatic
        fun getConnectionListener() = this.connectivityReceiverListener

        @JvmStatic
        fun setConnectionListener(connectivityReceiverListener: ConnectivityReceiverListener) {
            this.connectivityReceiverListener = connectivityReceiverListener
        }
    }
}