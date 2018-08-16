package com.drkdagron.commanderdeckmanager

import android.arch.persistence.room.Room
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.TextView
import com.drkdagron.commanderdeckmanager.db.*
import com.drkdagron.commanderdeckmanager.db.decks.AddDeckTask
import com.drkdagron.commanderdeckmanager.db.decks.Deck
import com.drkdagron.commanderdeckmanager.db.decks.GetDecksTask
import com.drkdagron.commanderdeckmanager.db.decks.UpdateDeckTask
import com.drkdagron.commanderdeckmanager.db.games.AddGameTask
import com.drkdagron.commanderdeckmanager.db.games.Game

class MainPresenter(val view: MainActivity) : DBComplete {

    var listView: ListView = view.findViewById(R.id.main_deck_list)
    var listEmpty: TextView = view.findViewById(R.id.main_deck_empty)
    lateinit var deckAdapter: DeckAdapter


    fun gotoHistory(dID: Long) {
        var intent = Intent(view.applicationContext, HistoryActivity::class.java)
        var bun = Bundle()
        bun.putLong("DECK_ID", dID)
        intent.putExtras(bun)
        view.startActivity(intent)
    }

    fun showEmptyListText() {
        listEmpty.visibility = View.VISIBLE
        listView.visibility = View.GONE
    }
    fun hideEmptyListText() {
        listEmpty.visibility = View.GONE
        listView.visibility = View.VISIBLE
    }

    fun displayDeckCreateDialog() {
        var d = DeckCreateDialog()
        d.pres = this
        d.show(view.fragmentManager, "create")
    }
    fun modifyDeckDialog(deck: Deck) {
        var d = DeckCreateDialog()
        d.pres = this
        d.deck = deck
        d.show(view.fragmentManager, "modify")
    }
    fun displayGameHistoryDialog(deck: Deck) {
        var d = GameHistoryDialog()
        d.deck = deck
        d.pres = this
        d.show(view.fragmentManager, "history")
    }

    override fun TaskComplete(id: Int, result: Any?) {
        if (id == DB.GET_DECK_LIST_ID) {
            deckAdapter = DeckAdapter(view.applicationContext, result as List<Deck>)
            deckAdapter.pres = this
            deckAdapter.fragmentManager = view.fragmentManager
            listView.adapter = deckAdapter

            if (deckAdapter.count == 0) {
                showEmptyListText()
            } else {
                hideEmptyListText()
            }
        } else if (id == DB.ADD_DECK_ID) {
            getDeckList()
        } else if (id == DB.UPDATE_DECK_ID) {
            getDeckList()
        } else if (id == DB.ADD_GAME_ID) {
            getDeckList()
        } else if (id == DB.DELETE_DECK_ID) {
            if (result as Int > 0) {
                getDeckList()
            }
        }
    }
    fun addNewDeck(d : Deck) {
        AddDeckTask(this).execute(d)
    }
    fun updateDeck(d: Deck) {
        UpdateDeckTask(this).execute(d)
    }
    fun getDeckList() {
        GetDecksTask(this).execute()
    }

    fun addNewGame(g: Game, d: Deck) { //gotta update the game table then modify the player
        AddGameTask(this).execute(g, d)
    }
}