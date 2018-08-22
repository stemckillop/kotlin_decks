package com.drkdagron.commanderdeckmanager.db.games

import android.os.AsyncTask
import com.drkdagron.commanderdeckmanager.db.DB
import com.drkdagron.commanderdeckmanager.db.DBComplete

class UpdateGameTask(var complete: DBComplete) : AsyncTask<Any, Void, Int>() {
    override fun doInBackground(vararg params: Any?): Int {
        return DB.DBTasks.mainDB.gameDao().updateGame(params[0] as Game)
    }

    override fun onPostExecute(result: Int?) {
        complete.TaskComplete(DB.UPDATE_GAME_ID, result)
    }
}