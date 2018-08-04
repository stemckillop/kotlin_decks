package com.drkdagron.commanderdeckmanager.db.games

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface GameDAO {
    @Query("SELECT * FROM gameData")
    fun getGamesAll() : List<Game>

    @Query("SELECT * FROM gameData WHERE deckID = :deckID")
    fun getGamesFor(deckID: Long) : List<Game>

    @Insert
    fun insertGame(g: Game)

    @Delete
    fun deleteGame(g: Game) : Int

    //tasks
}