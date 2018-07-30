package com.drkdagron.commanderdeckmanager

import android.app.AlertDialog
import android.app.DialogFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.drkdagron.commanderdeckmanager.db.Deck

class DeckCreateDialog : DialogFragment() {

    lateinit var pres: MainPresenter
    lateinit var commanderText: EditText
    var deck: Deck? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var view = inflater!!.inflate(R.layout.dialog_deck_create, null)

        if (deck != null) {
            view.findViewById<EditText>(R.id.edt_dialog_deck_name).setText(deck!!.name)
            view.findViewById<EditText>(R.id.edt_dialog_deck_commander).setText(deck!!.commander)
        }

        commanderText = view.findViewById(R.id.edt_dialog_deck_commander)

        var a = resources.getStringArray(R.array.formats)
        var spin = view.findViewById<Spinner>(R.id.spin_dialog_format)
        spin.adapter = ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, a)
        spin.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    if (position > 0) {
                        commanderText.visibility = View.GONE
                    } else {
                        commanderText.visibility = View.VISIBLE
                    }
                }
            }

        view.findViewById<Button>(R.id.btn_dialog_add).setOnClickListener {
            AlertDialog.Builder(activity).setTitle("Add Deck")
                    .setMessage("Are you sure you want to add this deck?")
                    .setPositiveButton("Yes", {dialog, which ->
                        buildDeck()
                        dismiss()
                    })
                    .setNegativeButton("No", { dialog, which ->
                        dismiss()
                    }).show()
        }

        view.findViewById<Button>(R.id.btn_dialog_cancel).setOnClickListener {
            dismiss()
        }

        return view
    }

    fun getIdentity(): String {
        var s = ""
        if (view.findViewById<Switch>(R.id.swiW).isChecked) {
            s += "W"
        }
        if (view.findViewById<Switch>(R.id.swiU).isChecked) {
            s += "U"
        }
        if (view.findViewById<Switch>(R.id.swiB).isChecked) {
            s += "B"
        }
        if (view.findViewById<Switch>(R.id.swiR).isChecked) {
            s += "R"
        }
        if (view.findViewById<Switch>(R.id.swiG).isChecked) {
            s += "G"
        }
        if (view.findViewById<Switch>(R.id.swiA).isChecked) {
            s += "A"
        }
        return s
    }

    fun buildDeck() {
        var d = Deck()
        d.active = 1
        d.name = view.findViewById<TextView>(R.id.edt_dialog_deck_name).editableText.toString()
        d.info = view.findViewById<TextView>(R.id.edt_dialog_deck_type).editableText.toString()
        d.identity = getIdentity()
        d.type = view.findViewById<Spinner>(R.id.spin_dialog_format).selectedItemPosition
        d.commander = view.findViewById<EditText>(R.id.edt_dialog_deck_commander).editableText.toString()

        if (d.name == "") {
            Toast.makeText(activity, "Deck needs a name", Toast.LENGTH_SHORT).show()
        } else if (d.identity == "") {
            Toast.makeText(activity, "Deck needs a color identity", Toast.LENGTH_SHORT).show()
        } else {
            pres.addNewDeck(d)
        }
    }
}