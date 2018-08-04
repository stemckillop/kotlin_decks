package com.drkdagron.commanderdeckmanager

import android.arch.persistence.room.Room
import android.view.View
import android.widget.ListView
import android.widget.TextView
import com.drkdagron.commanderdeckmanager.db.*
import com.drkdagron.commanderdeckmanager.db.decks.AddDeckTask
import com.drkdagron.commanderdeckmanager.db.decks.Deck
import com.drkdagron.commanderdeckmanager.db.decks.GetDecksTask
import com.drkdagron.commanderdeckmanager.db.decks.UpdateDeckTask

class MainPresenter(val view: MainActivity) : DBComplete {

    var listView: ListView = view.findViewById(R.id.main_deck_list)
    var listEmpty: TextView = view.findViewById(R.id.main_deck_empty)
    lateinit var deckAdapter: DeckAdapter
    var mainDB : DB = Room.databaseBuilder(view.applicationContext, DB::class.java, "roomDB").build()

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
        }
    }
    fun addNewDeck(d : Deck) {
        AddDeckTask(this).execute(mainDB, d)
    }
    fun updateDeck(d: Deck) {
        UpdateDeckTask(this).execute(mainDB, d)
    }
    fun getDeckList() {
        GetDecksTask(this).execute(mainDB)
    }
}