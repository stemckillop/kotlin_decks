package com.drkdagron.commanderdeckmanager.db.decks

import android.os.AsyncTask
import com.drkdagron.commanderdeckmanager.db.DB
import com.drkdagron.commanderdeckmanager.db.DBComplete

class UpdateDeckTask(var complete: DBComplete) : AsyncTask<Any, Void, Void>() {

    override fun doInBackground(vararg params: Any?): Void? {
        var b = (params[0] as DB).deckDao().updateTask(params[1]!! as Deck)
        return null
    }

    override fun onPostExecute(result: Void?) {
        complete.TaskComplete(DB.UPDATE_DECK_ID, result)
    }
}