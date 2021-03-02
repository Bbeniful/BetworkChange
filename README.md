# BetworkChange

This is a simple network listener for android. You have 2 options how would you like to watch the statesd
Firs option with listener:

## Requirements:
- android 5.0 and above
- AndroidX

You need to add this Listener.

## Activity:
```
class MainActivity: AppCompatActivity(),BetworkChecker.ConnectivityReceiverListener {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
           //
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

## Fragment:
```
class MyFragment: Fragment(),,BetworkChecker.ConnectivityReceiverListener {

  override fun onResume() {
           super.onResume()
           BetworkChecker.setConnectionListener(this)
        }
}
```

You also can use live data this way:

## Activity:
```
class MainActivity: AppCompatActivity(){

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
           //
            BetworkRegister.registerBetwork(this)

            BetworkChecker.getNetworkStateAsLiveData().observe(this, {it: Boolean->
                biding.liveDataText.text = getString(R.string.network_state, it)
            })

            BetworkChecker.getNetworkTypeAsLiveData().observe(this, { type: NetwortkType ->
                Toast.makeText(applicationContext, "$type", Toast.LENGTH_SHORT).show()
            })
        }


}
```

## Fragment:
```
class MyFragment: Fragment(),,BetworkChecker.ConnectivityReceiverListener {

  override fun onResume() {
           super.onResume()
           BetworkChecker.setConnectionListener(this)
        }
}
```


You also able to get the netwrok type as you can see above. There is the type definitions:
```
    WIFI,
    INTERNET,
    BLUETOOTH,
    NO_NETWORK,
    NONE
```


Feel free to use it and give me some feedback if you find something wrong or just create a pull request.


