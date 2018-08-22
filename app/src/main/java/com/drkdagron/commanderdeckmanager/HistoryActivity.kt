package com.drkdagron.commanderdeckmanager

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.drkdagron.commanderdeckmanager.db.decks.Deck

class HistoryActivity : AppCompatActivity() {

    lateinit var pres: HistoryPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        var d = intent.getSerializableExtra("DECK") as Deck
        pres = HistoryPresenter(this, d )
        pres.getGameList()
    }
}
