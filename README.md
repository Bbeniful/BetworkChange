# BetworkChange

This is a simple network listener for android. You have 2 options how would you like to watch the statesd
Firs option with listener:

You need to add this Listener.

Activity:
```
class MainActivity: AppCompatActivity(),BetworkChecker.ConnectivityReceiverListener {


     override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            biding = ActivityListenerBinding.inflate(layoutInflater)
            setContentView(biding.root)
            BetworkRegister.registerBetwork(this)
        }

     override fun onResume() {
            super.onResume()
            BetworkChecker.setConnectionListener(this)
        }

      override fun networkConnectionChanged(isConnected: Boolean) {
         TODO("Not yet implemented")
     }

        override fun getNetworkType(type: NetworkType) {
         TODO("Not yet implemented")
     }

}
```

Fragment:
```
class MyFragment: Fragment(),,BetworkChecker.ConnectivityReceiverListener {



  override fun onResume() {
           super.onResume()
           BetworkChecker.setConnectionListener(this)
        }

}
```


