package info.calc.finanziellefreiheitrechner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.InterstitialAd

/*
class SplashActivity : AppCompatActivity() {

    // This is the loading time of the splash screen
    private val timeout:Long = 3000 // 1 sec
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        Handler().postDelayed({
            // This method will be executed once the timer is over
            // Start your app main activity

            startActivity(Intent(this,MainActivity::class.java))

            // close this activity
            finish()

        }, timeout)
    }
}*/

import androidx.core.os.postDelayed
import java.util.*
import kotlin.concurrent.schedule

class SplashActivity : AppCompatActivity() {

    var timer = Timer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        timer.schedule(3000) {
            var intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onPause() {
        timer.cancel()
        super.onPause()
    }
}