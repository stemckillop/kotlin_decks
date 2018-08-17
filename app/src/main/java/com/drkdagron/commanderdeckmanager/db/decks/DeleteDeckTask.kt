package com.drkdagron.commanderdeckmanager.db.decks

import android.os.AsyncTask
import com.drkdagron.commanderdeckmanager.db.DB
import com.drkdagron.commanderdeckmanager.db.DBComplete

class DeleteDeckTask(var complete: DBComplete) : AsyncTask<Any, Void, Int>() {

    //params[0] == context
    //params[1] == deck

    override fun doInBackground(vararg params: Any?): Int {
        var d: Deck = params[0]!! as Deck

        DB.DBTasks.mainDB.gameDao().deleteGamesFor(d.id!!)
        return DB.DBTasks.mainDB.deckDao().deleteDeck(d)
    }

    override fun onPostExecute(result: Int?) {
        complete.TaskComplete(DB.DELETE_DECK_ID, result)
    }
}