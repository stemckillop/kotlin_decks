package com.drkdagron.commanderdeckmanager

import android.arch.persistence.room.Room
import android.widget.ListView
import com.drkdagron.commanderdeckmanager.db.DB
import com.drkdagron.commanderdeckmanager.db.DBComplete
import com.drkdagron.commanderdeckmanager.db.decks.Deck
import com.drkdagron.commanderdeckmanager.db.games.Game
import com.drkdagron.commanderdeckmanager.db.games.GetGameTask

class HistoryPresenter(val view: HistoryActivity, val deckID: Long) : DBComplete {

    val listView = view.findViewById<ListView>(R.id.history_game_list)
    lateinit var gameAdapter : HistoryAdapter

    override fun TaskComplete(id: Int, result: Any?) {
        if (id == DB.GET_GAMES_LIST_ID) {
            gameAdapter = HistoryAdapter(view.applicationContext, result as List<Game>)
            listView.adapter = gameAdapter
        }
    }

    fun getGameList() {
        GetGameTask(this).execute(deckID)
    }

}