package com.drkdagron.commanderdeckmanager.db.games

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.drkdagron.commanderdeckmanager.db.decks.Deck

@Dao
interface GameDAO {
    @Query("SELECT * FROM gameData")
    fun getGamesAll() : List<Game>

    @Query("SELECT * FROM gameData WHERE deckID = :deckID")
    fun getGamesFor(deckID: Long) : List<Game>

    @Insert
    fun insertGame(g: Game)

    @Query("DELETE FROM gameData WHERE deckID = :deckID")
    fun deleteGamesFor(deckID: Long) : Int

    @Update(onConflict = REPLACE)
    fun updateGame(g: Game) : Int

    @Delete
    fun deleteGame(g: Game) : Int
}