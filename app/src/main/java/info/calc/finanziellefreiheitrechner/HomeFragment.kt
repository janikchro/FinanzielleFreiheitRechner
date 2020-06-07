package info.calc.finanziellefreiheitrechner

import android.os.Bundle
import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.calc.finanziellefreiheitrechner.R
import com.google.android.gms.ads.*

/**
 * A simple [Fragment] subclass.
 */
/*class HomeFragment : Fragment() {
    private lateinit var mInterstitialAd: InterstitialAd
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
        }

}*/
class HomeFragment : Fragment(){
    private lateinit var mInterstitialAd: InterstitialAd
    override fun onCreateView(Layoutinflater: LayoutInflater,
                              container: ViewGroup?, savedInstantState: Bundle?): View? {
        val view: View = Layoutinflater!!.inflate(R.layout.fragment_home, container, false)

        return view
    }


    }
