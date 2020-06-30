package info.calc.finanziellefreiheitrechner

import android.os.Bundle
import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.google.android.gms.ads.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_ohne.*

class HomeFragment : Fragment(){
    private lateinit var mInterstitialAd: InterstitialAd
    override fun onCreateView(Layoutinflater: LayoutInflater,
                              container: ViewGroup?, savedInstantState: Bundle?): View? {

        val view: View = Layoutinflater.inflate(R.layout.fragment_home, container, false)
        return view
    }


    }
