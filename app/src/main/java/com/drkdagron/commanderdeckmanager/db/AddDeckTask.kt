package com.drkdagron.commanderdeckmanager.db

import android.arch.persistence.room.Room
import android.content.Context
import android.os.AsyncTask

class AddDeckTask(var complete: DBComplete) : AsyncTask<Any, Void, Unit>() {

    //params[0] == context
    //params[1] == deck

    override fun doInBackground(vararg params: Any?): Unit {
        return Room.databaseBuilder(params[0]!! as Context, DB::class.java, "deckDB").build().deckDao().insertDeck(params[1]!! as Deck)
    }

    override fun onPostExecute(result: Unit?) {
        complete.TaskComplete(DB.ADD_DECK_ID, null)
    }
}