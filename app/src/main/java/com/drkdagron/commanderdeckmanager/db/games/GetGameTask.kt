package com.drkdagron.commanderdeckmanager.db.games

import android.os.AsyncTask
import com.drkdagron.commanderdeckmanager.db.DB
import com.drkdagron.commanderdeckmanager.db.DBComplete
import com.drkdagron.commanderdeckmanager.db.decks.Deck

class GetGameTask(var complete: DBComplete) : AsyncTask<Any, Void, List<Game>>() {

    override fun doInBackground(vararg params: Any?): List<Game> {
        return (params[0] as DB).gameDao().getGamesFor((params[1] as Long))
    }

    override fun onPostExecute(result: List<Game>?) {
        complete.TaskComplete(DB.GET_DECK_LIST_ID, result)
    }
}