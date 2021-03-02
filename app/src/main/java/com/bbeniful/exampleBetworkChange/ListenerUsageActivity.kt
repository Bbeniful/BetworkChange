package com.bbeniful.exampleBetworkChange

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bbeniful.betworklistener.enums.NetworkType
import com.bbeniful.betworklistener.listener.BetworkChecker
import com.bbeniful.exampleBetworkChange.databinding.ActivityListenerBinding

class ListenerUsageActivity : AppCompatActivity(), BetworkChecker.ConnectivityReceiverListener {

    private lateinit var biding: ActivityListenerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        biding = ActivityListenerBinding.inflate(layoutInflater)
        setContentView(biding.root)

        registerReceiver(BetworkChecker(), IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    override fun onResume() {
        super.onResume()
        /**
         * You need to initialise the listener
         * **/
        BetworkChecker.connectivityReceiverListener = this
    }


    /**
     * Every time when the network state has changed
     * **/
    override fun networkConnectionChanged(isConnected: Boolean) {
        biding.listenerText.text = getString(R.string.network_state, isConnected)
    }

    override fun getNetworkType(type: NetworkType) {
        Toast.makeText(applicationContext, "$type", Toast.LENGTH_SHORT).show()
    }
}