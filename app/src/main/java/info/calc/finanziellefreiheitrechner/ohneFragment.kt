package info.calc.finanziellefreiheitrechner

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.SeekBar
import android.widget.Switch
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_mit.*
import kotlinx.android.synthetic.main.fragment_ohne.*
import kotlin.math.round

class ohneFragment : Fragment(), SeekBar.OnSeekBarChangeListener {
    var MAX_Depot = 200000
    var MAX_Ausgaben = 10000
    var MAX_Sparbetrag = 5000
    var MAX_Steuer = 50
    var Step_Steuer=0.25
    var MAX_Zins = 20
    var Step_Zins=0.25
    var Step_Sparbetrag = 100
    var Step_Depot = 1000
    var Step_Ausgaben = 100

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ohne, container, false)
        // Inflate the layout for this fragment

        val seekBarDepotwert: SeekBar? = view?.findViewById(R.id.sBDepotwert)
        val seekBarSparbetrag: SeekBar? = view?.findViewById(R.id.sBSparbetrag)
        val seekBarZinssatz: SeekBar? = view?.findViewById(R.id.sBZinssatz)
        val seekBarAbgeltungssteuer: SeekBar? = view?.findViewById(R.id.sBAbgeltungssteuer)
        val seekBarAusgaben: SeekBar? = view?.findViewById(R.id.sBAusgaben)
        seekBarSparbetrag!!.max = (MAX_Sparbetrag/Step_Sparbetrag)
        seekBarSparbetrag.progress=1000/Step_Sparbetrag
        seekBarAusgaben!!.max = (MAX_Ausgaben/Step_Ausgaben)
        seekBarAusgaben.progress = 2500/Step_Ausgaben
        seekBarDepotwert!!.max = (MAX_Depot/Step_Depot)
        seekBarDepotwert.progress=10000/Step_Depot
        seekBarAbgeltungssteuer!!.max = (MAX_Steuer/Step_Steuer).toInt()
        seekBarZinssatz!!.max = (MAX_Zins/Step_Zins).toInt()
        seekBarAbgeltungssteuer.progress = (26/Step_Steuer).toInt()
        seekBarZinssatz.progress = (4/Step_Zins).toInt()

        seekBarDepotwert.setOnSeekBarChangeListener(this)
        seekBarAbgeltungssteuer.setOnSeekBarChangeListener(this)
        seekBarSparbetrag.setOnSeekBarChangeListener(this)
        seekBarZinssatz.setOnSeekBarChangeListener(this)
        seekBarAusgaben.setOnSeekBarChangeListener(this)

        return view
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

        val sparbetrag = sBSparbetrag.progress*Step_Sparbetrag
        val prozentsatz = sBZinssatz.progress*Step_Zins
        val steuer = sBAbgeltungssteuer.progress*Step_Steuer
        val ausgaben = sBAusgaben.progress*Step_Ausgaben
        val depotwert = sBDepotwert.progress*Step_Depot

        tvSparbetrag.text = "mtl. Sparbetrag: %d".format(sparbetrag)
        tvAbgeltungssteuer.text = "Abgeltungssteuersatz: %.2f".format(steuer)
        tvAusgaben.text = "mtl. Ausgaben: %d".format(ausgaben)
        tvDepotwert.text = "Depotwert: %d".format(depotwert)
        tvZinssatz.text = "Zinssatz: %.2f".format(prozentsatz)


        sw_abgeltungssteuer_flag?.setOnClickListener {
            //switchabgeltungssteuer_flag!!.toggle()
            if (sw_abgeltungssteuer_flag.isChecked) {
                sw_abgeltungssteuer_flag!!.isChecked = true
                sw_abgeltungssteuer_flag!!.text = "Fälligkeit: "
                sBSparbetrag.progress=sparbetrag/2/Step_Sparbetrag
                sBSparbetrag.progress=sparbetrag/Step_Sparbetrag
                tvFlag.text = "jährlich"
            } else {
                sw_abgeltungssteuer_flag.isChecked = false
                sw_abgeltungssteuer_flag!!.text = "Fälligkeit: "
                sBSparbetrag.progress=sparbetrag/2/Step_Sparbetrag
                sBSparbetrag.progress=sparbetrag/Step_Sparbetrag
                tvFlag.text = "bei Auszahlung"
            }
        }


        tvZwei.visibility = View.VISIBLE
        tvRentenbeginn.visibility = View.VISIBLE
        tvRentenbeginnResult.visibility = View.VISIBLE
        tvEndkapital.visibility = View.VISIBLE
        tvEndkapitalResult.visibility = View.VISIBLE

        //var zinssatz_monat=100.00*(Math.pow((1.00+prozentsatz/100.00),1.00/12.00)-1.00)
        val zinssatz_monat = prozentsatz/ 12
        //var zinsen_monat=0.00

        val zinszuwachs_monat: Double
        var depotzins_monat: Double
        var depotwert_neu: Double
        var zinsen_monat = 0.0
        var depot = depotwert.toDouble()
        var monate = 0.0


        zinszuwachs_monat = sparbetrag * zinssatz_monat / 100.00



                     while (zinsen_monat < ausgaben) {
                         monate++
                         depotzins_monat = (depot * zinssatz_monat / 100.00)
                         zinsen_monat = (depotzins_monat + zinszuwachs_monat)



                          val freistellungMonat=801.00/12.00
                          if(!sw_abgeltungssteuer_flag.isChecked)
                          {
                              depotwert_neu = zinsen_monat + sparbetrag + depot
                              depot = depotwert_neu
                              if(zinsen_monat>=freistellungMonat) {
                                  val rest=zinsen_monat-freistellungMonat
                                  zinsen_monat = freistellungMonat + (rest * (1.00 - steuer / 100.00))
                              }
                          }
                          else {
                              if(zinsen_monat>=freistellungMonat){
                                  val rest=zinsen_monat-freistellungMonat
                                  zinsen_monat= freistellungMonat + (rest * (1.00 - steuer / 100.00))
                              }
                              depotwert_neu = zinsen_monat + sparbetrag + depot
                              depot = depotwert_neu
                          }
                      }



                      tvRentenbeginnResult.text = "%.2f Jahren".format((monate / 12))
                      tvEndkapitalResult.text = "%.2f€".format(depot)

    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {

    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {


    }

}
