package com.drkdagron.commanderdeckmanager.db.decks

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.drkdagron.commanderdeckmanager.db.decks.Deck

@Dao
interface DeckDAO {
    @Query("SELECT * from deckData")
    fun getAll(): List<Deck>

    @Query("SELECT * FROM deckData WHERE active = 1")
    fun getAllActive() : List<Deck>

    @Insert
    fun insertDeck(d: Deck)

    @Update(onConflict = REPLACE)
    fun updateTask(d: Deck) : Int

    @Delete
    fun deleteDeck(d: Deck) : Int
}