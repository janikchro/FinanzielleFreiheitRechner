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
import com.calc.finanziellefreiheitrechner.R
import kotlinx.android.synthetic.main.fragment_ohne.*
import kotlin.math.round

class ohneFragment : Fragment(), SeekBar.OnSeekBarChangeListener {
    var MAX_Depot = 200000
    var MAX_Ausgaben = 10000
    var MAX_Sparbetrag = 5000
    var MAX_Steuer = 50
    var MAX_Zins = 20

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater?.inflate(R.layout.fragment_ohne, container, false)
        // Inflate the layout for this fragment

        val seekBarDepotwert: SeekBar? = view?.findViewById(R.id.sBDepotwert)
        val seekBarSparbetrag: SeekBar? = view?.findViewById(R.id.sBSparbetrag)
        val seekBarZinssatz: SeekBar? = view?.findViewById(R.id.sBZinssatz)
        val seekBarAbgeltungssteuer: SeekBar? = view?.findViewById(R.id.sBAbgeltungssteuer)
        val seekBarAusgaben: SeekBar? = view?.findViewById(R.id.sBAusgaben)
        seekBarSparbetrag!!.max = MAX_Sparbetrag
        seekBarAusgaben!!.max = MAX_Ausgaben
        seekBarAusgaben!!.progress = 2500
        seekBarDepotwert!!.max = MAX_Depot
        seekBarAbgeltungssteuer!!.max = MAX_Steuer
        seekBarZinssatz!!.max = MAX_Zins
        seekBarAbgeltungssteuer!!.progress = 26
        seekBarZinssatz!!.progress = 4

        seekBarDepotwert?.setOnSeekBarChangeListener(this)
        seekBarAbgeltungssteuer?.setOnSeekBarChangeListener(this)
        seekBarSparbetrag?.setOnSeekBarChangeListener(this)
        seekBarZinssatz?.setOnSeekBarChangeListener(this)
        seekBarAusgaben?.setOnSeekBarChangeListener(this)

        val editTextSparbetrag: EditText?=view?.findViewById(R.id.inpSparbetrag)
        editTextSparbetrag!!.hint=seekBarSparbetrag.progress.toString()
        editTextSparbetrag?.setOnClickListener{v->editTextSparbetrag}
        editTextSparbetrag.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                if(editTextSparbetrag.text.isNotBlank()) {
                    if(editTextSparbetrag.text.toString().toInt()>MAX_Sparbetrag)
                    {
                        editTextSparbetrag?.setText(MAX_Sparbetrag.toString())

                    }
                    else {

                    }
                }
                else{
                    editTextSparbetrag.hint=100.toString()
                }
            }
            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                editTextSparbetrag.setSelection(editTextSparbetrag.text.length)
            }
        })

        val editTextDepotwert: EditText?=view?.findViewById(R.id.inpDepotwert)
        editTextDepotwert!!.hint=seekBarDepotwert.progress.toString()
        editTextDepotwert?.setOnClickListener{v->editTextDepotwert}
        editTextDepotwert.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                if(editTextDepotwert.text.isNotBlank()) {
                    if(editTextDepotwert.text.toString().toInt()>MAX_Depot)
                    {
                        editTextDepotwert?.setText(MAX_Depot.toString())

                    }
                    else {

                    }
                }
                else{
                    editTextDepotwert.hint=2000.toString()
                }
            }
            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                editTextDepotwert.setSelection(editTextDepotwert.text.length)
            }
        })
        val editTextAusgaben: EditText?=view?.findViewById(R.id.inpAusgaben)
        editTextAusgaben!!.hint=(seekBarAusgaben.progress).toString()
        editTextAusgaben?.setOnClickListener{v->editTextAusgaben}
        editTextAusgaben.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                if(editTextAusgaben.text.isNotBlank()) {
                    if(editTextAusgaben.text.toString().toInt()>MAX_Ausgaben)
                    {
                        editTextAusgaben?.setText(MAX_Ausgaben.toString())

                    }
                    else {

                    }
                }
                else{
                    editTextDepotwert.hint=0.toString()
                }
            }
            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                editTextAusgaben.setSelection(editTextAusgaben.text.length)
            }
        })

        val editTextSteuer: EditText?=view?.findViewById(R.id.inpAbgeltungssteuer)
        editTextSteuer!!.hint=seekBarAbgeltungssteuer.progress.toString()
        editTextSteuer?.setOnClickListener{v->editTextSteuer}
        editTextSteuer.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                if(editTextSteuer.text.isNotBlank()) {
                    if(editTextSteuer.text.toString().toFloat()>MAX_Steuer.toFloat())
                    {
                        editTextSteuer!!.setText(MAX_Steuer.toDouble().toString())

                    }
                    else {
                    }
                }
                else{
                    editTextSteuer.hint=26.00.toString()
                }
            }
            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                editTextSteuer.setSelection(editTextSteuer.text.length)
            }
        })

        val editTextZins: EditText?=view?.findViewById(R.id.inpZinssatz)
        editTextZins!!.hint=seekBarZinssatz.progress.toString()
        editTextZins?.setOnClickListener{v->editTextZins}
        editTextZins.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                if(editTextZins.text.isNotBlank()) {
                    if(editTextZins.text.toString().toFloat()>MAX_Steuer.toFloat())
                    {
                        editTextZins!!.setText(MAX_Steuer.toDouble().toString())

                    }
                    else {
                    }
                }
                else{
                    editTextZins.hint=4.00.toString()
                }
            }
            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
                editTextZins.setSelection(editTextZins.text.length)
            }
            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
            }
        })
        return view
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

        val sparbetrag = sBSparbetrag.progress
        val prozentsatz = sBZinssatz.progress
        val steuer = sBAbgeltungssteuer.progress
        val ausgaben = sBAusgaben.progress
        val depotwert = sBDepotwert.progress


        sw_abgeltungssteuer_flag?.setOnClickListener {
            //switchabgeltungssteuer_flag!!.toggle()
            if (sw_abgeltungssteuer_flag.isChecked) {
                sw_abgeltungssteuer_flag!!.isChecked = true
                sw_abgeltungssteuer_flag!!.text = "Fälligkeit: "
                sBSparbetrag.progress=200
                sBSparbetrag.progress=sparbetrag
                tvFlag.text = "jährlich"
            } else {
                sw_abgeltungssteuer_flag.isChecked = false
                sw_abgeltungssteuer_flag!!.text = "Fälligkeit: "
                sBSparbetrag.progress=200
                sBSparbetrag.progress=sparbetrag
                tvFlag.text = "bei Auszahlung"
            }
        }


        tvZwei.visibility = View.VISIBLE
        tvRentenbeginn.visibility = View.VISIBLE
        tvRentenbeginnResult.visibility = View.VISIBLE
        tvEndkapital.visibility = View.VISIBLE
        tvEndkapitalResult.visibility = View.VISIBLE

        //var zinssatz_monat=100.00*(Math.pow((1.00+prozentsatz/100.00),1.00/12.00)-1.00)
        var zinssatz_monat = prozentsatz / 12
        //var zinsen_monat=0.00

        var zinszuwachs_monat: Double
        var depotzins_monat: Double
        var depotwert_neu: Double
        var zinsen_monat = 0.0
        var depot = depotwert.toDouble()
        var monate = 0.0


        zinszuwachs_monat = sparbetrag.toDouble() * zinssatz_monat / 100.00

        while (zinsen_monat < ausgaben) {
            monate++
            depotzins_monat = depot * zinssatz_monat / 100.00
            zinsen_monat = depotzins_monat + zinszuwachs_monat

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
