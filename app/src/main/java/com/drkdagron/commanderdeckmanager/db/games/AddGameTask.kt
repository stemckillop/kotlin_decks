package com.drkdagron.commanderdeckmanager.db.games

import android.os.AsyncTask
import com.drkdagron.commanderdeckmanager.db.DB
import com.drkdagron.commanderdeckmanager.db.DBComplete
import com.drkdagron.commanderdeckmanager.db.decks.Deck

class AddGameTask(var complete: DBComplete) : AsyncTask<Any, Void, Unit>() {

    //params[0] == context
    //params[1] == game

    override fun doInBackground(vararg params: Any?): Unit {
        var g = params[0]!! as Game
        var d = params[1]!! as Deck

        DB.DBTasks.mainDB.gameDao().insertGame(g)

        d.games++
        d.last = g.place

        DB.DBTasks.mainDB.deckDao().updateTask(d)
        return
    }

    override fun onPostExecute(result: Unit?) {
        complete.TaskComplete(DB.ADD_GAME_ID, null)
    }
}