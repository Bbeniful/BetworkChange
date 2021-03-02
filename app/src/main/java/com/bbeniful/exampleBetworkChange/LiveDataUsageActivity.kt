package com.bbeniful.exampleBetworkChange

import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bbeniful.betworklistener.listener.BetworkChecker
import com.bbeniful.betworklistener.listener.BetworkChecker.Companion.type
import com.bbeniful.exampleBetworkChange.databinding.ActivityLiveDataBinding

class LiveDataUsageActivity : AppCompatActivity() {

    private lateinit var biding: ActivityLiveDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        biding = ActivityLiveDataBinding.inflate(layoutInflater)
        setContentView(biding.root)
        /**
         * You need to register this receiver
         * **/
        registerReceiver(
            BetworkChecker(),
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
        /**
         * This is how you can subscribe for live data and use the result
         * **/
        BetworkChecker.betWorkLiveCheck.observe(this, {
            biding.liveDataText.text = getString(R.string.network_state, it)
        })

        BetworkChecker.betWorkLiveType.observe(this, {
            Toast.makeText(applicationContext,"$type",Toast.LENGTH_SHORT).show()
        })
    }
}