package com.drkdagron.commanderdeckmanager.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.drkdagron.commanderdeckmanager.db.decks.Deck
import com.drkdagron.commanderdeckmanager.db.decks.DeckDAO
import com.drkdagron.commanderdeckmanager.db.games.Game
import com.drkdagron.commanderdeckmanager.db.games.GameDAO

@Database(entities = arrayOf(Deck::class, Game::class), version = 3)
abstract class DB: RoomDatabase() {
    abstract fun deckDao(): DeckDAO
    abstract fun gameDao(): GameDAO

    companion object DBTasks {
        lateinit var mainDB : DB
        fun initDB(ctx: Context) {
            mainDB = Room.databaseBuilder(ctx, DB::class.java, "roomDB").build() as DB
        }

        val GET_DECK_LIST_ID = 100
        val ADD_DECK_ID = 101
        val UPDATE_DECK_ID = 102

        val GET_GAMES_LIST_ID = 200
        val GET_GAMES_LIST_FROM_ID = 201
        val ADD_GAME_ID = 202
        val DELETE_GAME_ID = 203
    }
}

