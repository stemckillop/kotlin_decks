package com.drkdagron.commanderdeckmanager

import android.arch.persistence.room.Room
import android.widget.ListView
import com.drkdagron.commanderdeckmanager.db.DB

class MainPresenter(val view: MainActivity) {

    lateinit var db : DB

    fun getDeckList() {
        db = Room.databaseBuilder(view.applicationContext, DB::class.java, "deckDB").build()
        view.findViewById<ListView>(R.id.main_deck_list).adapter = DeckAdapter(view.applicationContext, db.deckDao().getAllActive())
    }

}