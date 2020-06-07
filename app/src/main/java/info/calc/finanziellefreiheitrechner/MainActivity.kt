package info.calc.finanziellefreiheitrechner


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import android.view.View
import androidx.fragment.app.Fragment
import com.calc.finanziellefreiheitrechner.R
import com.google.android.gms.ads.*
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    replaceFragment(HomeFragment())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_mit -> {
                    replaceFragment(mitFragment())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_ohne -> {
                    replaceFragment(ohneFragment())
                    return@OnNavigationItemSelectedListener true
                }

            }
            true


        }
    lateinit var mInterstitialAd: InterstitialAd
    lateinit var mAdView: AdView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nav_view.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        nav_view.selectedItemId =
            R.id.navigation_home;

        MobileAds.initialize(this) {}
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

           mInterstitialAd = InterstitialAd(this)
       mInterstitialAd.adUnitId = "ca-app-pub-4473032816994536/4446572663"
        //Sample ID mInterstitialAd.adUnitId = "ca-app-pub-3940256099942544/1033173712"

        mInterstitialAd.loadAd(AdRequest.Builder().build())
        mInterstitialAd.adListener = object: AdListener() {
            override fun onAdLoaded() {
                if (mInterstitialAd.isLoaded) {
                    mInterstitialAd.show()
                } else {
                    //Log.d("TAG", "The interstitial wasn't loaded yet.")
                }
            }
        }
        replaceFragment(HomeFragment())


    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer,fragment)
        fragmentTransaction.commit()
    }
    fun View.toggleVisibility() {
        if (visibility == View.VISIBLE) {
            visibility = View.INVISIBLE
        } else {
            visibility = View.VISIBLE
        }
    }

}
