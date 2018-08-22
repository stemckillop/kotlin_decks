package com.drkdagron.commanderdeckmanager

import android.app.DialogFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.drkdagron.commanderdeckmanager.db.decks.Deck
import com.drkdagron.commanderdeckmanager.db.games.Game
import kotlinx.android.synthetic.main.dialog_game_history.*
import kotlinx.android.synthetic.main.dialog_game_history.view.*
import java.time.LocalDateTime
import java.util.*

class GameHistoryDialog : DialogFragment() {

    lateinit var deck: Deck
    lateinit var pres: Presenter
    var game: Game? = null
    lateinit var noteView : EditText

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var view = inflater!!.inflate(R.layout.dialog_game_history, null)
        noteView = view.findViewById<EditText>(R.id.edt_dialog_notes)

        if (game != null) {
            view.findViewById<TextView>(R.id.edt_dialog_title).text = "Modify Game Result"
            noteView.setText(game!!.notes)
        }

        view.findViewById<ImageButton>(R.id.btn_first_place).setOnClickListener {
            buildGameHistory(1)
        }
        view.findViewById<ImageButton>(R.id.btn_second_place).setOnClickListener {
            buildGameHistory(2)
        }
        view.findViewById<ImageButton>(R.id.btn_third_place).setOnClickListener {
            buildGameHistory(3)
        }
        view.findViewById<Button>(R.id.btn_last_place).setOnClickListener {
            buildGameHistory(4)
        }

        return view
    }

    fun buildGameHistory(p : Int){

        if (game == null) {
            var g = Game()
            g.deckID = deck.id!!
            g.place = p
            g.notes = noteView.text.toString()
            g.time = System.currentTimeMillis() / 1000
            (pres as MainPresenter).addNewGame(g, deck!!)
        } else {
            game!!.place = p
            game!!.notes = noteView.text.toString()
            (pres as HistoryPresenter).modifyGameHistory(game!!)
        }
        dismiss()
    }
}