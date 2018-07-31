package com.drkdagron.commanderdeckmanager.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update

@Dao
interface DeckDAO {
    @Query("SELECT * from deckData")
    fun getAll(): List<Deck>

    @Query("SELECT * FROM deckData WHERE active = 1")
    fun getAllActive() : List<Deck>

    @Insert
    fun insertDeck(d: Deck)

    @Update(onConflict = REPLACE)
    fun updateTask(d: Deck)
}