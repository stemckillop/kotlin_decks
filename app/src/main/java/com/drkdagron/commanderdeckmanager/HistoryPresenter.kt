package com.drkdagron.commanderdeckmanager

import android.arch.persistence.room.Room
import android.util.Log
import android.widget.ListView
import com.drkdagron.commanderdeckmanager.db.DB
import com.drkdagron.commanderdeckmanager.db.DBComplete
import com.drkdagron.commanderdeckmanager.db.decks.Deck
import com.drkdagron.commanderdeckmanager.db.games.Game
import com.drkdagron.commanderdeckmanager.db.games.GetGameTask
import com.drkdagron.commanderdeckmanager.db.games.UpdateGameTask

class HistoryPresenter(val view: HistoryActivity, val deck: Deck) : Presenter(), DBComplete {

    val listView = view.findViewById<ListView>(R.id.history_game_list)
    lateinit var gameAdapter : HistoryAdapter

    override fun TaskComplete(id: Int, result: Any?) {
        if (id == DB.GET_GAMES_LIST_ID) {
            gameAdapter = HistoryAdapter(view.applicationContext, result as List<Game>)
            gameAdapter.pres = this
            listView.adapter = gameAdapter
        } else if (id == DB.UPDATE_GAME_ID) {
            getGameList()
        }
    }

    fun getGameList() {
        GetGameTask(this).execute(deck.id)
    }

    fun modifyGameHistoryView(g: Game) {
        var dialog = GameHistoryDialog()
        dialog.deck = deck
        dialog.pres = this
        dialog.game = g
        dialog.show(view.fragmentManager, "ModifyHistory")
    }

    fun modifyGameHistory(g: Game) {
        UpdateGameTask(this).execute(g)
    }
}