package com.drkdagron.commanderdeckmanager.db.games

import android.os.AsyncTask
import com.drkdagron.commanderdeckmanager.db.DB
import com.drkdagron.commanderdeckmanager.db.DBComplete
import com.drkdagron.commanderdeckmanager.db.decks.Deck

class AddGameTask(var complete: DBComplete) : AsyncTask<Any, Void, Unit>() {

    //params[0] == context
    //params[1] == game

    override fun doInBackground(vararg params: Any?): Unit {
        var g = params[1]!! as Game
        var d = params[2]!! as Deck

        (params[0] as DB).gameDao().insertGame(g)

        d.games++
        d.last = g.place

        (params[0] as DB).deckDao().updateTask(d)
        return
    }

    override fun onPostExecute(result: Unit?) {
        complete.TaskComplete(DB.ADD_GAME_ID, null)
    }
}