package com.drkdagron.commanderdeckmanager.db.decks

import android.os.AsyncTask
import com.drkdagron.commanderdeckmanager.db.DB
import com.drkdagron.commanderdeckmanager.db.DBComplete

class GetDecksTask(var complete: DBComplete) : AsyncTask<Any, Void, List<Deck>>() {

    override fun doInBackground(vararg params: Any?): List<Deck> {
        return (params[0] as DB).deckDao().getAll()
    }

    override fun onPostExecute(result: List<Deck>?) {
        complete.TaskComplete(DB.GET_DECK_LIST_ID, result)
    }
}