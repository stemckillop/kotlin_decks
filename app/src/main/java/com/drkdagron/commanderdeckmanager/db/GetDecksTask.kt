package com.drkdagron.commanderdeckmanager.db

import android.arch.persistence.room.Room
import android.content.Context
import android.os.AsyncTask
import android.widget.ListView
import com.drkdagron.commanderdeckmanager.MainActivity

class GetDecksTask(var complete: DBComplete) : AsyncTask<Any, Void, List<Deck>>() {

    override fun doInBackground(vararg params: Any?): List<Deck> {
        return Room.databaseBuilder(params[0]!! as Context, DB::class.java, "deckDB").build().deckDao().getAll()
    }

    override fun onPostExecute(result: List<Deck>?) {
        complete.TaskComplete(DB.GET_DECK_LIST_ID, result)
    }
}