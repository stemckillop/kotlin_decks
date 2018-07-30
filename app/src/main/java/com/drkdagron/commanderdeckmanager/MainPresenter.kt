package com.drkdagron.commanderdeckmanager

import android.app.DialogFragment
import android.arch.persistence.room.Room
import android.os.AsyncTask
import android.view.View
import android.widget.ListView
import android.widget.TextView
import com.drkdagron.commanderdeckmanager.db.*

class MainPresenter(val view: MainActivity) : DBComplete {

    var listView: ListView = view.findViewById(R.id.main_deck_list)
    var listEmpty: TextView = view.findViewById(R.id.main_deck_empty)
    lateinit var deckAdapter: DeckAdapter

    override fun TaskComplete(id: Int, result: Any?) {
        if (id == DB.GET_DECK_LIST_ID) {
            deckAdapter = DeckAdapter(view.applicationContext, result as List<Deck>)
            deckAdapter.fragmentManager = view.fragmentManager
            listView.adapter = deckAdapter

            if (deckAdapter.count == 0) {
                showEmptyListText()
            } else {
                hideEmptyListText()
            }
        } else if (id == DB.ADD_DECK_ID) {
            getDeckList()
        }
    }

    lateinit var db : DB

    fun getDeckList() {
        GetDecksTask(this).execute(view.applicationContext)
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
        var dialog: DialogFragment = DeckCreateDialog()
        (dialog as DeckCreateDialog).pres = this
        dialog.show(view.fragmentManager, "CreateDeck")
    }

    fun addNewDeck(d : Deck) {
        AddDeckTask(this).execute(view.applicationContext, d)
    }
}