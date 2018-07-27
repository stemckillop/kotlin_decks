package com.drkdagron.commanderdeckmanager.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = arrayOf(Deck::class), version = 1)
abstract class DB: RoomDatabase() {
    abstract fun deckDao(): DeckDAO
}