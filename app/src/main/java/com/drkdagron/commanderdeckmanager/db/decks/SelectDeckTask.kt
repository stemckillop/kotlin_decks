package com.drkdagron.commanderdeckmanager.db.decks

import android.os.AsyncTask
import com.drkdagron.commanderdeckmanager.db.DB
import com.drkdagron.commanderdeckmanager.db.DBComplete

class SelectDeckTask(val complete: DBComplete) : AsyncTask<Any, Void, Deck>() {
    override fun doInBackground(vararg params: Any?): Deck {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onPostExecute(result: Deck?) {
        complete.TaskComplete(DB.GET_DECK_ID, result)
    }
}