package com.drkdagron.commanderdeckmanager.db

import android.arch.persistence.room.Room
import android.content.Context
import android.os.AsyncTask

class UpdateDeckTask(var complete: DBComplete) : AsyncTask<Any, Void, Void>() {

    override fun doInBackground(vararg params: Any?): Void? {
        Room.databaseBuilder(params[0]!! as Context, DB::class.java, "deckDB").build().deckDao().updateTask(params[1]!! as Deck)
        return null
    }

    override fun onPostExecute(result: Void?) {
        complete.TaskComplete(DB.UPDATE_DECK_ID, result)
    }
}