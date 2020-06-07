package info.calc.finanziellefreiheitrechner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import com.calc.finanziellefreiheitrechner.R
import kotlinx.android.synthetic.main.fragment_mit.*

class mitFragment : Fragment(), SeekBar.OnSeekBarChangeListener {
    var STEP = 100
    var MAX_Sparbetrag_mit = 5000


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater?.inflate(R.layout.fragment_mit, container, false)
// Inflate the layout for this fragment

        val seekBarSparbetrag_mit: SeekBar? = view?.findViewById(R.id.sBSparbetrag_mit)
        seekBarSparbetrag_mit!!.max = MAX_Sparbetrag_mit / STEP
        seekBarSparbetrag_mit?.setOnSeekBarChangeListener(this)
        return view
    }
    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

        val sparbetrag_mit = sBSparbetrag_mit.progress * STEP
        tvSparbetrag_mit.text = "mtl. Sparbetrag: %d€".format(sparbetrag_mit)
        tvEndkapitalResult_mit.visibility = View.VISIBLE
        tvEndkapital_mit.visibility = View.VISIBLE
        tvEndkapitalResult_mit.text = "kommt bald!"
    }
    override fun onStartTrackingTouch(seekBar: SeekBar?) {

    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
    }
}

