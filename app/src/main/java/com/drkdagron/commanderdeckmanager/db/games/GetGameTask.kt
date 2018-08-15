package com.drkdagron.commanderdeckmanager.db.games

import android.os.AsyncTask
import android.util.Log
import com.drkdagron.commanderdeckmanager.db.DB
import com.drkdagron.commanderdeckmanager.db.DBComplete
import com.drkdagron.commanderdeckmanager.db.decks.Deck

class GetGameTask(var complete: DBComplete) : AsyncTask<Any, Void, List<Game>>() {

    override fun doInBackground(vararg params: Any?): List<Game> {
        return DB.DBTasks.mainDB.gameDao().getGamesFor((params[0] as Long))
    }

    override fun onPostExecute(result: List<Game>?) {
        complete.TaskComplete(200, result)
    }
}