package com.drkdagron.commanderdeckmanager.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = arrayOf(Deck::class), version = 2)
abstract class DB: RoomDatabase() {
    abstract fun deckDao(): DeckDAO

    companion object DBTasks {
        val GET_DECK_LIST_ID = 100
        val ADD_DECK_ID = 101
        val UPDATE_DECK_ID = 102
    }
}

