package com.drkdagron.commanderdeckmanager

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class HistoryActivity : AppCompatActivity() {

    lateinit var pres: HistoryPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        pres = HistoryPresenter(this, intent.getBundleExtra().getLong("DECK_ID"))
    }
}
