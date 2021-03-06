package com.drkdagron.commanderdeckmanager.db.decks

import android.os.AsyncTask
import com.drkdagron.commanderdeckmanager.db.DB
import com.drkdagron.commanderdeckmanager.db.DBComplete

class AddDeckTask(var complete: DBComplete) : AsyncTask<Any, Void, Unit>() {

    //params[0] == context
    //params[1] == deck

    override fun doInBackground(vararg params: Any?): Unit {
        return DB.DBTasks.mainDB.deckDao().insertDeck(params[0]!! as Deck)
    }

    override fun onPostExecute(result: Unit?) {
        complete.TaskComplete(DB.ADD_DECK_ID, null)
    }
}