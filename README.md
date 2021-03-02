# BetworkChange

This is a simple network state listener for Android. You have 2 options how you would like to watch the states of network.


## Requirements:
- android 5.0 and above
- AndroidX


## Usage
Add it in your root build.gradle at the end of repositories:
```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

Step 2. Add the dependency

```
	dependencies {
	        implementation 'com.github.Bbeniful:BetworkChange:1.0.0'
	}
```

## First option with listener,you need to add this Listener.

### Activity:
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

### Fragment:
```
class MyFragment: Fragment(),,BetworkChecker.ConnectivityReceiverListener {

  override fun onResume() {
           super.onResume()
           BetworkChecker.setConnectionListener(this)
        }
}
```

## You can also use LiveData this way:

### Activity:
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

### Fragment:
```
class MyFragment: Fragment(),,BetworkChecker.ConnectivityReceiverListener {

     override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
        //

         BetworkChecker.getNetworkStateAsLiveData().observe(this, {it: Boolean->
              biding.liveDataText.text = getString(R.string.network_state, it)
          })

          BetworkChecker.getNetworkTypeAsLiveData().observe(this, { type: NetwortkType ->
                  Toast.makeText(applicationContext, "$type", Toast.LENGTH_SHORT).show()
          })

        }

    override fun onResume() {
           super.onResume()
           BetworkRegister.registerBetwork(this)
        }
}
```


You are also able to get the network types as you can see above. There is the type definitions:
```
    WIFI,
    INTERNET,
    BLUETOOTH,
    NO_NETWORK,
    NONE
```

Feel free to use it and give me some feedback if you find something wrong or just create a pull request.


