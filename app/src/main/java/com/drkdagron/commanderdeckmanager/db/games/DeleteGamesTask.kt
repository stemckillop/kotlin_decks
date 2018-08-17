package com.drkdagron.commanderdeckmanager.db.games

import android.os.AsyncTask
import com.drkdagron.commanderdeckmanager.db.DB
import com.drkdagron.commanderdeckmanager.db.DBComplete
import com.drkdagron.commanderdeckmanager.db.decks.Deck

class DeleteGamesTask(var complete: DBComplete) : AsyncTask<Any, Void, Unit>() {
    override fun doInBackground(vararg params: Any?): Unit {
        val d = params[0]!! as Deck

        DB.DBTasks.mainDB.gameDao().deleteGamesFor(d.id!!)
        return
    }

    override fun onPostExecute(result: Unit?) {
        complete.TaskComplete(DB.DELETE_GAME_ID, null)
    }
}